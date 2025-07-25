/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.types

import org.jetbrains.kotlin.builtins.functions.AllowedToUsedOnlyInK1
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind
import org.jetbrains.kotlin.config.LanguageFeature
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.fir.declarations.utils.modality
import org.jetbrains.kotlin.fir.diagnostics.ConeIntermediateDiagnostic
import org.jetbrains.kotlin.fir.isPrimitiveNumberOrUnsignedNumberType
import org.jetbrains.kotlin.fir.languageVersionSettings
import org.jetbrains.kotlin.fir.resolve.createSubstitutionForSupertype
import org.jetbrains.kotlin.fir.resolve.fullyExpandedType
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
import org.jetbrains.kotlin.fir.resolve.providers.symbolProvider
import org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
import org.jetbrains.kotlin.fir.resolve.substitution.createTypeSubstitutorByTypeConstructor
import org.jetbrains.kotlin.fir.resolve.substitution.substitutorByMap
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl
import org.jetbrains.kotlin.fir.utils.exceptions.withConeTypeEntry
import org.jetbrains.kotlin.name.StandardClassIds
import org.jetbrains.kotlin.types.AbstractTypeChecker
import org.jetbrains.kotlin.types.AbstractTypeRefiner
import org.jetbrains.kotlin.types.TypeCheckerState
import org.jetbrains.kotlin.types.model.*
import org.jetbrains.kotlin.utils.DFS
import org.jetbrains.kotlin.utils.addToStdlib.shouldNotBeCalled
import org.jetbrains.kotlin.utils.exceptions.errorWithAttachment

interface ConeInferenceContext : TypeSystemInferenceExtensionContext, ConeTypeContext {

    val symbolProvider: FirSymbolProvider get() = session.symbolProvider

    override fun nullableNothingType(): ConeClassLikeType {
        return session.builtinTypes.nullableNothingType.coneType
    }

    override fun nullableAnyType(): ConeClassLikeType {
        return session.builtinTypes.nullableAnyType.coneType
    }

    override fun nothingType(): ConeClassLikeType {
        return session.builtinTypes.nothingType.coneType
    }

    override fun anyType(): ConeClassLikeType {
        return session.builtinTypes.anyType.coneType
    }

    override fun createFlexibleType(lowerBound: RigidTypeMarker, upperBound: RigidTypeMarker): KotlinTypeMarker {
        require(lowerBound is ConeRigidType)
        require(upperBound is ConeRigidType)

        return coneFlexibleOrSimpleType(this, lowerBound, upperBound, isTrivial = false)
    }

    override fun createTrivialFlexibleTypeOrSelf(lowerBound: KotlinTypeMarker): KotlinTypeMarker {
        require(lowerBound is ConeKotlinType)

        // We need to ensure that the returned type is not a flexible type with two equal bounds.
        // This can happen if the given type is marked nullable or if it's an error type.
        return if (lowerBound is ConeRigidType && !lowerBound.isMarkedNullable && lowerBound !is ConeErrorType) {
            lowerBound.toTrivialFlexibleType(this)
        } else {
            lowerBound
        }
    }

    override fun isTriviallyFlexible(flexibleType: FlexibleTypeMarker): Boolean {
        require(flexibleType is ConeFlexibleType)
        return flexibleType.isTrivial
    }

    override fun makeLowerBoundDefinitelyNotNullOrNotNull(flexibleType: FlexibleTypeMarker): KotlinTypeMarker {
        require(flexibleType is ConeFlexibleType)

        if (flexibleType.isTrivial) {
            return ConeFlexibleType(
                flexibleType.lowerBound.makeConeTypeDefinitelyNotNullOrNotNull(this) as ConeRigidType,
                flexibleType.upperBound,
                isTrivial = true
            )
        }

        return super.makeLowerBoundDefinitelyNotNullOrNotNull(flexibleType)
    }

    override fun createSimpleType(
        constructor: TypeConstructorMarker,
        arguments: List<TypeArgumentMarker>,
        nullable: Boolean,
        isExtensionFunction: Boolean,
        contextParameterCount: Int,
        attributes: List<AnnotationMarker>?
    ): SimpleTypeMarker {
        require(constructor is ConeTypeConstructorMarker)
        var attributesList = attributes?.filterIsInstanceTo<ConeAttribute<*>, _>(mutableListOf())
        val coneAttributes: ConeAttributes = if (isExtensionFunction || contextParameterCount > 0) {
            require(constructor is ConeClassLikeLookupTag)
            // We don't want to create new instance of ConeAttributes which
            //   contains only CompilerConeAttributes.ExtensionFunctionType
            //   to avoid memory consumption
            if (attributesList.isNullOrEmpty() && contextParameterCount == 0) {
                ConeAttributes.WithExtensionFunctionType
            } else {
                if (attributesList == null) attributesList = mutableListOf()
                if (isExtensionFunction) attributesList += CompilerConeAttributes.ExtensionFunctionType
                if (contextParameterCount > 0) attributesList += CompilerConeAttributes.ContextFunctionTypeParams(contextParameterCount)
                ConeAttributes.create(attributesList)
            }
        } else {
            attributesList?.let { ConeAttributes.create(it) } ?: ConeAttributes.Empty
        }
        @Suppress("UNCHECKED_CAST")
        return when (constructor) {
            is ConeClassLikeLookupTag -> ConeClassLikeTypeImpl(
                constructor,
                (arguments as List<ConeTypeProjection>).toTypedArray(),
                nullable,
                coneAttributes,
            )
            is ConeTypeParameterLookupTag -> ConeTypeParameterTypeImpl(
                constructor,
                nullable,
                coneAttributes
            )
            is ConeIntersectionType -> if (coneAttributes === constructor.attributes) {
                constructor
            } else {
                ConeIntersectionType(
                    constructor.intersectedTypes.map { it.withAttributes(coneAttributes) },
                    constructor.upperBoundForApproximation?.withAttributes(coneAttributes)
                )
            }
            is ConeCapturedTypeConstructor,
            is ConeIntegerLiteralType,
            is ConeStubTypeConstructor,
            is ConeTypeVariableTypeConstructor,
                -> error("Unsupported type constructor: ${constructor::class}")
            is ConeClassifierLookupTag
                -> error("Unexpected /* sealed */ ConeClassifierLookupTag inheritor: ${constructor::class}")
        }
    }

    override fun createTypeArgument(type: KotlinTypeMarker, variance: TypeVariance): TypeArgumentMarker {
        require(type is ConeKotlinType)
        return when (variance) {
            TypeVariance.INV -> type
            TypeVariance.IN -> ConeKotlinTypeProjectionIn(type)
            TypeVariance.OUT -> ConeKotlinTypeProjectionOut(type)
        }
    }

    override fun createStarProjection(typeParameter: TypeParameterMarker): TypeArgumentMarker {
        return ConeStarProjection
    }

    override fun newTypeCheckerState(
        errorTypesEqualToAnything: Boolean,
        stubTypesEqualToAnything: Boolean,
        dnnTypesEqualToFlexible: Boolean,
    ): TypeCheckerState = TypeCheckerState(
        errorTypesEqualToAnything,
        stubTypesEqualToAnything,
        dnnTypesEqualToFlexible,
        allowedTypeVariable = true,
        typeSystemContext = this,
        kotlinTypePreparator = ConeTypePreparator(session),
        kotlinTypeRefiner = AbstractTypeRefiner.Default
    )

    override fun KotlinTypeMarker.canHaveUndefinedNullability(): Boolean {
        require(this is ConeKotlinType)
        return this is ConeCapturedType || this is ConeTypeVariableType
                || this is ConeTypeParameterType
    }

    override fun RigidTypeMarker.isExtensionFunction(): Boolean {
        require(this is ConeRigidType)
        return this.isExtensionFunctionType
    }

    override fun StubTypeMarker.getOriginalTypeVariable(): TypeVariableTypeConstructorMarker {
        require(this is ConeStubType)
        return this.constructor.variable.typeConstructor
    }

    override fun KotlinTypeMarker.typeDepth(): Int {
        require(this is ConeKotlinType)
        return when (this) {
            is ConeSimpleKotlinType -> typeDepth()
            is ConeFlexibleType -> maxOf(lowerBound().typeDepth(), upperBound().typeDepth())
            is ConeDefinitelyNotNullType -> original.typeDepth()
        }
    }

    override fun KotlinTypeMarker.typeDepthForApproximation(): Int {
        return if (this is ConeCapturedType) {
            constructor.projection.type?.typeDepth() ?: 1
        } else {
            typeDepth()
        }
    }

    override fun RigidTypeMarker.typeDepth(): Int {
        require(this is ConeRigidType)

        if (this is ConeClassLikeType) {
            val fullyExpanded = fullyExpandedType(session)
            if (this !== fullyExpanded) {
                return fullyExpanded.typeDepth()
            }
        }

        var maxArgumentDepth = 0
        for (arg in typeArguments) {
            val current = if (arg is ConeStarProjection) 1 else (arg as ConeKotlinTypeProjection).type.typeDepth()
            if (current > maxArgumentDepth) {
                maxArgumentDepth = current
            }
        }

        return maxArgumentDepth + 1
    }

    override fun KotlinTypeMarker.contains(predicate: (KotlinTypeMarker) -> Boolean): Boolean {
        return this.containsInternal(predicate)
    }

    private fun KotlinTypeMarker?.containsInternal(
        predicate: (KotlinTypeMarker) -> Boolean,
    ): Boolean {
        if (this == null) return false

        if (predicate(this)) return true

        val flexibleType = this as? ConeFlexibleType
        if (flexibleType != null) {
            if (flexibleType.lowerBound.containsInternal(predicate)) {
                return true
            }
            if (!flexibleType.isTrivial && flexibleType.upperBound.containsInternal(predicate)) {
                return true
            }
        }


        if (this is ConeDefinitelyNotNullType && this.original.containsInternal(predicate)) {
            return true
        }

        if (this is ConeIntersectionType) {
            return this.intersectedTypes.any { it.containsInternal(predicate) }
        }

        if (this is ConeCapturedType) {
            return this.constructor.projection.type?.containsInternal(predicate) == true
        }

        repeat(argumentsCount()) { index ->
            val argument = getArgument(index)
            if (!argument.isStarProjection() && argument.getType().containsInternal(predicate)) return true
        }

        return false
    }

    private fun TypeConstructorMarker.isUnitTypeConstructor(): Boolean {
        return this is ConeClassLikeLookupTag && this.classId == StandardClassIds.Unit
    }

    override fun Collection<KotlinTypeMarker>.singleBestRepresentative(): KotlinTypeMarker? {
        if (this.size == 1) return this.first()

        val context = newTypeCheckerState(errorTypesEqualToAnything = true, stubTypesEqualToAnything = true)
        return this.firstOrNull { candidate ->
            this.all { other ->
                // We consider error types equal to anything here, so that intersections like
                // {Array<String>, Array<[ERROR]>} work correctly
                candidate == other || AbstractTypeChecker.equalTypes(context, candidate, other)
            }
        }
    }

    override fun KotlinTypeMarker.isUnit(): Boolean {
        require(this is ConeKotlinType)
        return this.typeConstructor().isUnitTypeConstructor() && !this.isMarkedOrFlexiblyNullable
    }

    override fun KotlinTypeMarker.isBuiltinFunctionTypeOrSubtype(): Boolean {
        require(this is ConeKotlinType)
        return this.isTypeOrSubtypeOf {
            (it.lowerBoundIfFlexible() as ConeKotlinType).isSomeFunctionType(session)
        }
    }

    override fun KotlinTypeMarker.withNullability(nullable: Boolean): KotlinTypeMarker {
        require(this is ConeKotlinType)
        return this.withNullability(nullable, this@ConeInferenceContext)
    }

    override fun KotlinTypeMarker.makeDefinitelyNotNullOrNotNull(preserveAttributes: Boolean): KotlinTypeMarker {
        require(this is ConeKotlinType)
        return makeConeTypeDefinitelyNotNullOrNotNull(this@ConeInferenceContext, preserveAttributes = preserveAttributes)
    }

    override fun RigidTypeMarker.makeDefinitelyNotNullOrNotNull(): RigidTypeMarker {
        require(this is ConeRigidType)
        return makeConeTypeDefinitelyNotNullOrNotNull(this@ConeInferenceContext) as RigidTypeMarker
    }

    override fun createCapturedType(
        constructorProjection: TypeArgumentMarker,
        constructorSupertypes: List<KotlinTypeMarker>,
        lowerType: KotlinTypeMarker?,
        captureStatus: CaptureStatus
    ): CapturedTypeMarker {
        require(lowerType is ConeKotlinType?)
        require(constructorProjection is ConeTypeProjection)
        @Suppress("UNCHECKED_CAST")
        return ConeCapturedType(
            constructor = ConeCapturedTypeConstructor(
                constructorProjection,
                lowerType, captureStatus,
                constructorSupertypes as List<ConeKotlinType>
            )
        )
    }

    override fun createStubTypeForBuilderInference(typeVariable: TypeVariableMarker): StubTypeMarker {
        shouldNotBeCalled("PCLA does not use stub types for builder inference")
    }

    override fun createStubTypeForTypeVariablesInSubtyping(typeVariable: TypeVariableMarker): StubTypeMarker {
        require(typeVariable is ConeTypeVariable) { "$typeVariable should subtype of ${ConeTypeVariable::class.qualifiedName}" }
        return ConeStubTypeForTypeVariableInSubtyping(typeVariable, typeVariable.defaultType().isMarkedNullable())
    }

    override fun KotlinTypeMarker.removeAnnotations(): KotlinTypeMarker {
        require(this is ConeKotlinType)
        return withAttributes(ConeAttributes.Empty)
    }

    override fun RigidTypeMarker.replaceArguments(newArguments: List<TypeArgumentMarker>): RigidTypeMarker {
        require(this is ConeRigidType)
        @Suppress("UNCHECKED_CAST")
        return this.withArguments((newArguments as List<ConeTypeProjection>).toTypedArray())
    }

    override fun RigidTypeMarker.replaceArguments(replacement: (TypeArgumentMarker) -> TypeArgumentMarker): RigidTypeMarker {
        require(this is ConeRigidType)
        return this.withArguments { replacement(it) as ConeTypeProjection }
    }

    override fun KotlinTypeMarker.hasExactAnnotation(): Boolean {
        require(this is ConeKotlinType)
        return attributes.exact != null
    }

    override fun KotlinTypeMarker.hasNoInferAnnotation(): Boolean {
        require(this is ConeKotlinType)
        return hasNoInfer
    }

    override fun TypeConstructorMarker.isFinalClassConstructor(): Boolean {
        val symbol = toClassLikeSymbol() ?: return false
        if (symbol is FirAnonymousObjectSymbol) return true
        val classSymbol = symbol as? FirRegularClassSymbol ?: return false
        return classSymbol.modality == Modality.FINAL
    }

    override fun TypeVariableMarker.freshTypeConstructor(): TypeVariableTypeConstructorMarker {
        require(this is ConeTypeVariable)
        return this.typeConstructor
    }

    override fun CapturedTypeMarker.typeConstructorProjection(): TypeArgumentMarker {
        require(this is ConeCapturedType)
        return this.constructor.projection
    }

    override fun CapturedTypeMarker.typeParameter(): TypeParameterMarker? {
        require(this is ConeCapturedType)
        return this.constructor.typeParameterMarker
    }

    @AllowedToUsedOnlyInK1
    override fun CapturedTypeMarker.withNotNullProjection(): KotlinTypeMarker {
        error("AllowedToUsedOnlyInK1")
    }

    @AllowedToUsedOnlyInK1
    override fun CapturedTypeMarker.isProjectionNotNull(): Boolean {
        return false
    }

    override fun CapturedTypeMarker.hasRawSuperType(): Boolean {
        require(this is ConeCapturedType)
        return constructor.supertypes?.any(ConeKotlinType::isRaw) == true
    }

    override fun DefinitelyNotNullTypeMarker.original(): ConeSimpleKotlinType {
        require(this is ConeDefinitelyNotNullType)
        return this.original
    }

    override fun typeSubstitutorByTypeConstructor(map: Map<TypeConstructorMarker, KotlinTypeMarker>): ConeSubstitutor {
        @Suppress("UNCHECKED_CAST")
        return createTypeSubstitutorByTypeConstructor(
            map as Map<TypeConstructorMarker, ConeKotlinType>, this, approximateIntegerLiterals = false
        )
    }

    override fun createEmptySubstitutor(): ConeSubstitutor {
        return ConeSubstitutor.Empty
    }

    override fun createSubstitutionFromSubtypingStubTypesToTypeVariables(): TypeSubstitutorMarker {
        return object : AbstractConeSubstitutor(this@ConeInferenceContext) {
            override fun substituteType(type: ConeKotlinType): ConeKotlinType? {
                return ((type as? ConeStubTypeForTypeVariableInSubtyping)
                    ?.constructor?.variable?.defaultType)?.withNullabilityOf(type, this@ConeInferenceContext)
            }
        }
    }

    override fun TypeSubstitutorMarker.safeSubstitute(type: KotlinTypeMarker): KotlinTypeMarker {
        require(this is ConeSubstitutor)
        require(type is ConeKotlinType)
        return this.substituteOrSelf(type)
    }

    override fun TypeVariableMarker.defaultType(): ConeTypeVariableType {
        require(this is ConeTypeVariable)
        return this.defaultType
    }

    override fun KotlinTypeMarker.isSpecial(): Boolean {
        // Cone type system doesn't have special types
        return false
    }

    override fun TypeConstructorMarker.isTypeVariable(): Boolean {
        return this is ConeTypeVariableTypeConstructor
    }

    override fun TypeVariableTypeConstructorMarker.isContainedInInvariantOrContravariantPositions(): Boolean {
        require(this is ConeTypeVariableTypeConstructor)
        return isContainedInInvariantOrContravariantPositions
    }

    override fun createErrorType(debugName: String, delegatedType: RigidTypeMarker?): ConeErrorType {
        return ConeErrorType(ConeIntermediateDiagnostic(debugName), delegatedType = delegatedType as ConeKotlinType?)
    }

    override fun createUninferredType(constructor: TypeConstructorMarker): KotlinTypeMarker {
        return ConeErrorType(ConeIntermediateDiagnostic("Uninferred type c: $constructor"))
    }

    override fun CapturedTypeMarker.captureStatus(): CaptureStatus {
        require(this is ConeCapturedType)
        return this.constructor.captureStatus
    }

    override fun CapturedTypeMarker.isOldCapturedType(): Boolean = false

    override fun TypeConstructorMarker.isCapturedTypeConstructor(): Boolean {
        return this is ConeCapturedTypeConstructor
    }

    override fun KotlinTypeMarker.eraseContainingTypeParameters(): KotlinTypeMarker {
        val typeParameterErasureMap = this.extractTypeParameters()
            .map { (it as ConeTypeParameterLookupTag).typeParameterSymbol }
            .eraseToUpperBoundsAssociated(session)
        val substitutor by lazy(LazyThreadSafetyMode.NONE) { substitutorByMap(typeParameterErasureMap, session) }
        val typeWithErasedTypeParameters = if (argumentsCount() != 0) {
            replaceArgumentsDeeply {
                val type = it.getType() ?: return@replaceArgumentsDeeply it
                val typeParameter =
                    (type.typeConstructor().getTypeParameterClassifier() as? ConeTypeParameterLookupTag)?.typeParameterSymbol
                if (typeParameter != null) {
                    createTypeArgument(substitutor.safeSubstitute(type), TypeVariance.OUT)
                } else it
            }
        } else if (typeConstructor().isTypeParameterTypeConstructor()) {
            substitutor.safeSubstitute(this)
        } else this

        return typeWithErasedTypeParameters
    }

    override fun TypeConstructorMarker.isTypeParameterTypeConstructor(): Boolean {
        return this.getTypeParameterClassifier() != null
    }

    override fun KotlinTypeMarker.removeExactAnnotation(): KotlinTypeMarker {
        require(this is ConeKotlinType)
        return withAttributes(attributes.remove(CompilerConeAttributes.Exact))
    }

    override fun TypeConstructorMarker.toErrorType(): ConeErrorType {
        if (this is ConeClassLikeLookupTag) return createErrorType("Not found classifier: $classId", delegatedType = null)
        return createErrorType("Unknown reason", delegatedType = null)
    }

    override fun findCommonIntegerLiteralTypesSuperType(explicitSupertypes: List<RigidTypeMarker>): RigidTypeMarker? {
        return ConeIntegerLiteralType.findCommonSuperType(explicitSupertypes)
    }

    override fun unionTypeAttributes(types: List<KotlinTypeMarker>): List<AnnotationMarker> {
        @Suppress("UNCHECKED_CAST")
        return (types as List<ConeKotlinType>).map { it.attributes }.reduce { x, y -> x.union(y) }.toList()
    }

    private fun AnnotationMarker.isCustomAttribute(): Boolean {
        val compilerAttributes = CompilerConeAttributes.classIdByCompilerAttributeKey
        return (this as? ConeAttribute<*>)?.key !in compilerAttributes &&
                this !is ParameterNameTypeAttribute &&
                this !is CustomAnnotationTypeAttribute
    }

    override fun KotlinTypeMarker.replaceCustomAttributes(newAttributes: List<AnnotationMarker>): KotlinTypeMarker {
        require(this is ConeKotlinType)
        @Suppress("UNCHECKED_CAST")
        val newCustomAttributes = (newAttributes as List<ConeAttribute<*>>).filter { it.isCustomAttribute() }
        val attributesToKeep = this.attributes.filterNot { it.isCustomAttribute() }
        return withAttributes(ConeAttributes.create(newCustomAttributes + attributesToKeep))
    }

    override fun TypeConstructorMarker.getApproximatedIntegerLiteralType(expectedType: KotlinTypeMarker?): KotlinTypeMarker {
        require(this is ConeIntegerLiteralType && expectedType is ConeKotlinType?)
        return this.getApproximatedType(expectedType)
    }

    override fun KotlinTypeMarker.isSignedOrUnsignedNumberType(): Boolean {
        require(this is ConeKotlinType)
        if (this is ConeIntegerLiteralType) return true
        if (this !is ConeClassLikeType) return false
        return isPrimitiveNumberOrUnsignedNumberType()
    }

    override fun KotlinTypeMarker.isFunctionOrKFunctionWithAnySuspendability(): Boolean {
        require(this is ConeKotlinType)
        return this.isSomeFunctionType(session)
    }

    fun ConeKotlinType.isTypeOrSubtypeOf(predicate: (ConeKotlinType) -> Boolean): Boolean {
        return predicate(this) || DFS.dfsFromNode(
            this,
            {
                // FIXME supertypes of type constructor contain unsubstituted arguments
                it.typeConstructor().supertypes()
            },
            DFS.VisitedWithSet(),
            object : DFS.AbstractNodeHandler<ConeKotlinType, Boolean>() {
                private var result = false

                override fun beforeChildren(current: ConeKotlinType): Boolean {
                    if (predicate(current)) {
                        result = true
                    }
                    return !result
                }

                override fun result() = result
            }
        )
    }

    override fun KotlinTypeMarker.isExtensionFunctionType(): Boolean {
        require(this is ConeKotlinType)
        return isExtensionFunctionType(session)
    }

    override fun KotlinTypeMarker.contextParameterCount(): Int {
        require(this is ConeKotlinType)
        return unwrapToSimpleTypeUsingLowerBound().contextParameterTypes(session).size
    }

    override fun KotlinTypeMarker.extractArgumentsForFunctionTypeOrSubtype(): List<KotlinTypeMarker> {
        val builtInFunctionType = getFunctionTypeFromSupertypes() as ConeKotlinType
        return buildList {
            // excluding return type
            for (index in 0 until builtInFunctionType.argumentsCount() - 1) {
                val type = when (val arg = builtInFunctionType.getArgument(index)) {
                    is ConeKotlinTypeProjection -> arg.type
                    else -> StandardClassIds.Any.constructClassLikeType(ConeTypeProjection.EMPTY_ARRAY, isMarkedNullable = true)
                }
                add(type)
            }
        }
    }

    override fun KotlinTypeMarker.getFunctionTypeFromSupertypes(): KotlinTypeMarker {
        require(this is ConeKotlinType)
        assert(this.isBuiltinFunctionTypeOrSubtype()) {
            "Not a function type or subtype: ${this.renderForDebugging()}"
        }

        val rigidType = fullyExpandedType(session).lowerBoundIfFlexible() as ConeRigidType

        return when {
            rigidType.isSomeFunctionType(session) -> this
            rigidType is ConeCapturedType -> {
                rigidType.constructor.supertypes?.firstNotNullOfOrNull { it.getFunctionTypeFromSupertypesOrNull() }
            }
            rigidType is ConeTypeParameterType -> {
                rigidType.lookupTag.typeParameterSymbol.resolvedBounds.firstNotNullOfOrNull { it.coneType.getFunctionTypeFromSupertypesOrNull() }
            }
            rigidType is ConeIntersectionType -> {
                rigidType.intersectedTypes.firstNotNullOfOrNull { it.getFunctionTypeFromSupertypesOrNull() }
            }
            else -> {
                var functionalSupertype: KotlinTypeMarker? = null
                rigidType.anySuperTypeConstructor { type ->
                    // `fastCorrespondingSupertypes` only works for `ConeClassLikeType`.
                    // We need a special case above for every type for which `TypeConstructorMarker.supertypes`
                    // returns something non-trivial.
                    rigidType.fastCorrespondingSupertypes(type.typeConstructor())?.any { superType ->
                        val isFunction = (superType as ConeKotlinType).isSomeFunctionType(session)
                        if (isFunction) {
                            functionalSupertype = superType
                        }
                        isFunction
                    } ?: false
                }
                functionalSupertype
            }
        } ?: errorWithAttachment(
            """
            Failed to find functional supertype for ${rigidType::class.java}.
            The contract of this function is that it returns a non-null value iff `isBuiltinFunctionTypeOrSubtype` returns `true`.
            """.trimIndent()
        ) {
            withConeTypeEntry("type", rigidType)
        }
    }

    private fun KotlinTypeMarker.getFunctionTypeFromSupertypesOrNull(): KotlinTypeMarker? {
        return if (isBuiltinFunctionTypeOrSubtype()) {
            getFunctionTypeFromSupertypes()
        } else {
            null
        }
    }

    override fun KotlinTypeMarker.functionTypeKind(): FunctionTypeKind? {
        require(this is ConeKotlinType)
        return (this.lowerBoundIfFlexible() as ConeClassLikeType).functionTypeKind(session)
    }

    override fun getNonReflectFunctionTypeConstructor(parametersNumber: Int, kind: FunctionTypeKind): TypeConstructorMarker {
        return kind.nonReflectKind().numberedClassId(parametersNumber).toLookupTag()
    }

    override fun getReflectFunctionTypeConstructor(parametersNumber: Int, kind: FunctionTypeKind): TypeConstructorMarker {
        return kind.reflectKind().numberedClassId(parametersNumber).toLookupTag()
    }

    override fun createTypeWithUpperBoundForIntersectionResult(
        firstCandidate: KotlinTypeMarker,
        secondCandidate: KotlinTypeMarker
    ): KotlinTypeMarker {
        require(firstCandidate is ConeKotlinType)
        require(secondCandidate is ConeKotlinType)
        val intersectionType = firstCandidate.lowerBoundIfFlexible() as? ConeIntersectionType ?: error {
            "Expected type is intersection, found $firstCandidate"
        }
        return intersectionType.withUpperBound(secondCandidate)
    }

    override fun RigidTypeMarker.getUpperBoundForApproximationOfIntersectionType(): KotlinTypeMarker? {
        return (this as? ConeIntersectionType)?.upperBoundForApproximation
    }

    override fun useRefinedBoundsForTypeVariableInFlexiblePosition(): Boolean = session.languageVersionSettings.supportsFeature(
        LanguageFeature.JavaTypeParameterDefaultRepresentationWithDNN
    )

    override fun usePreciseSimplificationToFlexibleLowerConstraint(): Boolean = session.languageVersionSettings.supportsFeature(
        LanguageFeature.PreciseSimplificationToFlexibleLowerConstraint
    )

    override fun KotlinTypeMarker.convertToNonRaw(): KotlinTypeMarker {
        require(this is ConeKotlinType)
        return this.convertToNonRawVersion()
    }

    override fun createSubstitutorForSuperTypes(baseType: KotlinTypeMarker): TypeSubstitutorMarker? =
        if (baseType is ConeLookupTagBasedType) createSubstitutionForSupertype(baseType, session) else null

    override fun supportsImprovedVarianceInCst(): Boolean {
        return session.languageVersionSettings.supportsFeature(LanguageFeature.ImprovedVarianceInCst)
    }

    override val isK2: Boolean
        get() = true
}
