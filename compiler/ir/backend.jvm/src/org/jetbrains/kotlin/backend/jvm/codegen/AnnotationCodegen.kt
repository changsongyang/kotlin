/*
 * Copyright 2010-2019 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.backend.jvm.codegen

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import org.jetbrains.kotlin.backend.common.ir.ir2string
import org.jetbrains.kotlin.backend.jvm.JvmLoweredDeclarationOrigin
import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.codegen.AsmUtil
import org.jetbrains.kotlin.codegen.state.GenerationState
import org.jetbrains.kotlin.config.JvmTarget
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.descriptors.annotations.*
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.types.*
import org.jetbrains.kotlin.ir.util.*
import org.jetbrains.kotlin.resolve.jvm.annotations.*
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.synthetic.isVisibleOutside
import org.jetbrains.kotlin.utils.DFS
import org.jetbrains.kotlin.utils.addToStdlib.safeAs
import org.jetbrains.org.objectweb.asm.*
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

abstract class AnnotationCodegen(
    private val innerClassConsumer: InnerClassConsumer,
    state: GenerationState
) {

    private val typeMapper = state.typeMapper

    class JvmFlagAnnotation(private val fqName: FqName, val jvmFlag: Int)

    /**
     * @param returnType can be null if not applicable (e.g. [annotated] is a class)
     */
    fun genAnnotations(annotated: IrAnnotationContainer?, returnType: Type?) {
        if (annotated == null) return

        val annotationDescriptorsAlreadyPresent = mutableSetOf<String>()

        val annotations = annotated.annotations

        for (annotation in annotations) {
            val applicableTargets = annotation.applicableTargetSet()
// Never seems to be invoked in the IR backend.
//            if (annotated is AnonymousFunctionDescriptor &&
//                KotlinTarget.FUNCTION !in applicableTargets &&
//                KotlinTarget.PROPERTY_GETTER !in applicableTargets &&
//                KotlinTarget.PROPERTY_SETTER !in applicableTargets
//            ) {
//                assert(KotlinTarget.EXPRESSION in applicableTargets) {
//                    "Inconsistent target list for lambda annotation: $applicableTargets on $annotated"
//                }
//                continue
//            }
            if (annotated is IrClass &&
                KotlinTarget.CLASS !in applicableTargets &&
                KotlinTarget.ANNOTATION_CLASS !in applicableTargets
            ) {
                if (annotated.visibility == Visibilities.LOCAL) {
                    assert(KotlinTarget.EXPRESSION in applicableTargets) {
                        "Inconsistent target list for object literal annotation: $applicableTargets on $annotated"
                    }
                    continue
                }
            }

            genAnnotation(annotation)?.let { descriptor ->
                annotationDescriptorsAlreadyPresent.add(descriptor)
            }
        }

        generateAdditionalAnnotations(annotated, returnType, annotationDescriptorsAlreadyPresent)
    }

    private fun generateAdditionalAnnotations(
        annotated: IrAnnotationContainer,
        returnType: Type?,
        annotationDescriptorsAlreadyPresent: MutableSet<String>
    ) {
        if (annotated is IrDeclaration) {
            if (returnType != null && !AsmUtil.isPrimitive(returnType)) {
                generateNullabilityAnnotationForCallable(annotated, annotationDescriptorsAlreadyPresent)
            }
        }
    }

    private fun generateNullabilityAnnotationForCallable(
        declaration: IrDeclaration, // There is no superclass that encompasses IrFunction, IrField and nothing else.
        annotationDescriptorsAlreadyPresent: MutableSet<String>
    ) {
        // No need to annotate privates, synthetic accessors and their parameters
        if (isInvisibleFromTheOutside(declaration)) return
        if (declaration is IrValueParameter && isInvisibleFromTheOutside(declaration.parent as? IrDeclaration)) return

        // No need to annotate annotation methods since they're always non-null
        if (declaration is IrSimpleFunction && declaration.correspondingProperty != null &&
            declaration.parentAsClass.isAnnotationClass
        ) {
            return
        }

        val type = when (declaration) {
            is IrFunction -> declaration.returnType
            is IrField -> declaration.type
            is IrValueDeclaration -> declaration.type
            else -> return
        }

        if (isBareTypeParameterWithNullableUpperBound(type)) {
            // This is to account for the case of, say
            //   class Function<R> { fun invoke(): R }
            // it would be a shame to put @Nullable on the return type of the function, and force all callers to check for null,
            // so we put no annotations
            return
        }

        val annotationClass = if (type.isNullable()) Nullable::class.java else NotNull::class.java

        generateAnnotationIfNotPresent(annotationDescriptorsAlreadyPresent, annotationClass)
    }

    private fun generateAnnotationIfNotPresent(annotationDescriptorsAlreadyPresent: MutableSet<String>, annotationClass: Class<*>) {
        val descriptor = Type.getType(annotationClass).descriptor
        if (!annotationDescriptorsAlreadyPresent.contains(descriptor)) {
            visitAnnotation(descriptor, false).visitEnd()
        }
    }

    fun generateAnnotationDefaultValue(value: IrExpression) {
        val visitor = visitAnnotation(null, false)  // Parameters are unimportant
        genCompileTimeValue(null, value, visitor)
        visitor.visitEnd()
    }

    private fun genAnnotation(annotation: IrCall): String? {
        val annotationClass = annotation.annotationClass
        val rp = getRetentionPolicy(annotationClass)
        if (rp == RetentionPolicy.SOURCE && !typeMapper.classBuilderMode.generateSourceRetentionAnnotations) {
            return null
        }

        innerClassConsumer.addInnerClassInfoFromAnnotation(annotationClass)

        val asmTypeDescriptor = typeMapper.mapType(annotation.type.toKotlinType()).descriptor
        val annotationVisitor = visitAnnotation(asmTypeDescriptor, rp == RetentionPolicy.RUNTIME)

        genAnnotationArguments(annotation, annotationVisitor)
        annotationVisitor.visitEnd()

        return asmTypeDescriptor
    }

    private fun genAnnotationArguments(annotation: IrCall, annotationVisitor: AnnotationVisitor) {
        val annotationClass = annotation.annotationClass
        for (param in annotation.symbol.owner.valueParameters) {
            val value = annotation.getValueArgument(param.index)
            if (value == null && param.hasDefaultValue()) continue // Default value will be supplied by JVM at runtime.
            if (value == null) error("No value for annotation parameter $param")
            genCompileTimeValue(getAnnotationArgumentJvmName(annotationClass, param.name), value, annotationVisitor)
        }
    }

    private fun getAnnotationArgumentJvmName(annotationClass: IrClass?, parameterName: Name): String {
        if (annotationClass == null) return parameterName.asString()

        val field =
            annotationClass.declarations.filterIsInstance<IrField>().singleOrNull { it.name == parameterName }
                ?: return parameterName.asString()

        return typeMapper.mapAnnotationParameterName(field.descriptor)
    }

    private fun genCompileTimeValue(
        name: String?,
        value: IrExpression,
        annotationVisitor: AnnotationVisitor
    ) {
        fun visitUnsupportedValue(value: IrExpression) {
            val mode = typeMapper.classBuilderMode
            if (mode.generateBodies) {
                throw IllegalStateException("Don't know how to compile annotation value ${ir2string(value)}")
            }
        }

        when (value) {
            is IrConst<*> -> annotationVisitor.visit(name, value.value)
            is IrCall -> {
                val callee = value.symbol.owner
                when {
                    callee is IrConstructor && callee.parentAsClass.isAnnotationClass -> {
                        val internalAnnName = typeMapper.mapType(callee.returnType.toKotlinType()).descriptor
                        val visitor = annotationVisitor.visitAnnotation(name, internalAnnName)
                        genAnnotationArguments(value, visitor)
                        visitor.visitEnd()
                    }
                    else -> error("Not supported!")
                }
            }
            is IrGetEnumValue -> {
                // TODO: Should use asmTypeForClassId, since fqName may fail (internal classes, local declarations).
                val enumClassInternalName =
                    AsmUtil.asmTypeByFqNameWithoutInnerClasses(value.symbol.owner.parentAsClass.fqNameWhenAvailable!!).descriptor
                val enumEntryName = value.symbol.owner.name
                annotationVisitor.visitEnum(name, enumClassInternalName, enumEntryName.asString())
            }
            is IrVararg -> { // array constructor
                val visitor = annotationVisitor.visitArray(name)
                for (element in value.elements) {
                    genCompileTimeValue(null, element as IrExpression, visitor)
                }
                visitor.visitEnd()
            }
            is IrClassReference -> {
                annotationVisitor.visit(name, typeMapper.mapType(value.classType.toKotlinType()))
            }
            is IrErrorExpression -> visitUnsupportedValue(value)
            else -> error("Unsupported compiletime value ${ir2string(value)}")
        }
    }

    abstract fun visitAnnotation(descr: String?, visible: Boolean): AnnotationVisitor

    companion object {
        val FIELD_FLAGS = listOf(
            JvmFlagAnnotation(VOLATILE_ANNOTATION_FQ_NAME, Opcodes.ACC_VOLATILE),
            JvmFlagAnnotation(TRANSIENT_ANNOTATION_FQ_NAME, Opcodes.ACC_TRANSIENT)
        )

        val METHOD_FLAGS = listOf(
            JvmFlagAnnotation(STRICTFP_ANNOTATION_FQ_NAME, Opcodes.ACC_STRICT),
            JvmFlagAnnotation(SYNCHRONIZED_ANNOTATION_FQ_NAME, Opcodes.ACC_SYNCHRONIZED)
        )

        val NO_ANNOTATION_VISITOR = object : AnnotationVisitor(Opcodes.API_VERSION) {
            override fun visitAnnotation(name: String, desc: String) = safe(super.visitAnnotation(name, desc))

            override fun visitArray(name: String) = safe(super.visitArray(name))
        }

        private fun safe(av: AnnotationVisitor?): AnnotationVisitor = av ?: NO_ANNOTATION_VISITOR

        private fun isInvisibleFromTheOutside(declaration: IrDeclaration?): Boolean {
            if (declaration is IrSimpleFunction && declaration.origin === JvmLoweredDeclarationOrigin.SYNTHETIC_ACCESSOR) {
                return true
            }
            if (declaration is IrDeclarationWithVisibility) {
                return !declaration.visibility.isVisibleOutside()
            }
            return false
        }

        private val annotationTargetMaps: Map<JvmTarget, MutableMap<KotlinTarget, ElementType>> =
            mapOf(
                JvmTarget.JVM_1_6 to mutableMapOf(
                    KotlinTarget.CLASS to ElementType.TYPE,
                    KotlinTarget.ANNOTATION_CLASS to ElementType.ANNOTATION_TYPE,
                    KotlinTarget.CONSTRUCTOR to ElementType.CONSTRUCTOR,
                    KotlinTarget.LOCAL_VARIABLE to ElementType.LOCAL_VARIABLE,
                    KotlinTarget.FUNCTION to ElementType.METHOD,
                    KotlinTarget.PROPERTY_GETTER to ElementType.METHOD,
                    KotlinTarget.PROPERTY_SETTER to ElementType.METHOD,
                    KotlinTarget.FIELD to ElementType.FIELD,
                    KotlinTarget.VALUE_PARAMETER to ElementType.PARAMETER
                ),
                // additional values for jvm8
                JvmTarget.JVM_1_8 to mutableMapOf(
                    KotlinTarget.TYPE_PARAMETER to ElementType.TYPE_PARAMETER,
                    KotlinTarget.TYPE to ElementType.TYPE_USE
                )
            )

        init {
            annotationTargetMaps[JvmTarget.JVM_1_8]!!.putAll(annotationTargetMaps[JvmTarget.JVM_1_6]!!.toList())
        }

        private val annotationRetentionMap = mapOf(
            KotlinRetention.SOURCE to RetentionPolicy.SOURCE,
            KotlinRetention.BINARY to RetentionPolicy.CLASS,
            KotlinRetention.RUNTIME to RetentionPolicy.RUNTIME
        )

        private fun getRetentionPolicy(irClass: IrClass): RetentionPolicy {
            val retention = irClass.getAnnotationRetention()
            if (retention != null) {
                return annotationRetentionMap[retention]!!
            }
            irClass.getAnnotation(FqName(java.lang.annotation.Retention::class.java.name))?.let { retentionAnnotation ->
                val value = retentionAnnotation.getValueArgument(0)
                if (value is IrEnumEntry) {
                    val enumClassFqName = value.parentAsClass.fqNameWhenAvailable
                    if (RetentionPolicy::class.java.name == enumClassFqName?.asString()) {
                        return RetentionPolicy.valueOf(value.name.asString())
                    }
                }
            }

            return RetentionPolicy.RUNTIME
        }

        fun forClass(
            cv: ClassVisitor,
            innerClassConsumer: InnerClassConsumer,
            state: GenerationState
        ) = object : AnnotationCodegen(innerClassConsumer, state) {
            override fun visitAnnotation(descr: String?, visible: Boolean) = safe(cv.visitAnnotation(descr, visible))
        }

        fun forMethod(
            mv: MethodVisitor,
            innerClassConsumer: InnerClassConsumer,
            state: GenerationState
        ) = object : AnnotationCodegen(innerClassConsumer, state) {
            override fun visitAnnotation(descr: String?, visible: Boolean) = safe(mv.visitAnnotation(descr, visible))
        }

        fun forField(
            fv: FieldVisitor,
            innerClassConsumer: InnerClassConsumer,
            state: GenerationState
        ) = object : AnnotationCodegen(innerClassConsumer, state) {
            override fun visitAnnotation(descr: String?, visible: Boolean) = safe(fv.visitAnnotation(descr, visible))
        }

        fun forParameter(
            mv: MethodVisitor,
            parameter: Int,
            innerClassConsumer: InnerClassConsumer,
            state: GenerationState
        ) = object : AnnotationCodegen(innerClassConsumer, state) {
            override fun visitAnnotation(descr: String?, visible: Boolean) = safe(
                mv.visitParameterAnnotation(parameter, descr, visible)
            )
        }

        fun forAnnotationDefaultValue(
            mv: MethodVisitor,
            innerClassConsumer: InnerClassConsumer,
            state: GenerationState
        ) = object : AnnotationCodegen(innerClassConsumer, state) {
            override fun visitAnnotation(descr: String?, visible: Boolean) = safe(mv.visitAnnotationDefault())
        }

        /* Temporary? */
        fun IrCall.applicableTargetSet() =
            annotationClass.applicableTargetSet() ?: KotlinTarget.DEFAULT_TARGET_SET

        val IrCall.annotationClass get() = symbol.owner.parentAsClass
    }
}

interface InnerClassConsumer {
    fun addInnerClassInfoFromAnnotation(irClass: IrClass)
}

private fun isBareTypeParameterWithNullableUpperBound(type: IrType): Boolean {
    return type.classifierOrNull?.owner is IrTypeParameter && !type.isMarkedNullable() && type.isNullable()
}


fun IrValueParameter.hasDefaultValue(): Boolean = DFS.ifAny(
    listOf(this),
    { param -> param.parent.safeAs<IrSimpleFunction>()?.overriddenSymbols?.map { it.owner.valueParameters[param.index] } ?: emptyList() },
    { param -> param.defaultValue != null}
)

private val RETENTION_PARAMETER_NAME = Name.identifier("value")

private fun IrClass.getAnnotationRetention(): KotlinRetention? {
    val retentionArgument =
        getAnnotation(KotlinBuiltIns.FQ_NAMES.retention)?.getValueArgument(RETENTION_PARAMETER_NAME)
                as? IrGetEnumValue?: return null
    val retentionArgumentValue = retentionArgument.symbol.owner
    return KotlinRetention.valueOf(retentionArgumentValue.name.asString())
}

// To be generalized to IrMemberAccessExpression as soon as properties get symbols.
private fun IrCall.getValueArgument(name: Name): IrExpression? {
    val index = symbol.owner.valueParameters.find { it.name == name }?.index ?: return null
    return getValueArgument(index)
}

// Copied and modified from AnnotationChecker.kt

private val TARGET_ALLOWED_TARGETS = Name.identifier("allowedTargets")

private fun IrClass.applicableTargetSet(): Set<KotlinTarget>? {
    val targetEntry = getAnnotation(KotlinBuiltIns.FQ_NAMES.target) ?: return null
    return loadAnnotationTargets(targetEntry)
}

private fun loadAnnotationTargets(targetEntry: IrCall): Set<KotlinTarget>? {
    val valueArgument = targetEntry.getValueArgument(TARGET_ALLOWED_TARGETS)
            as? IrVararg ?: return null
    return valueArgument.elements.filterIsInstance<IrGetEnumValue>().mapNotNull {
        KotlinTarget.valueOrNull(it.symbol.owner.name.asString())
    }.toSet()
}
