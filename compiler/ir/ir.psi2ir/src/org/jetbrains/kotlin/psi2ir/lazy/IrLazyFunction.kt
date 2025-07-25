/*
 * Copyright 2010-2025 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.psi2ir.lazy

import org.jetbrains.kotlin.descriptors.DescriptorVisibility
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.ObsoleteDescriptorBasedAPI
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter
import org.jetbrains.kotlin.ir.declarations.MetadataSource
import org.jetbrains.kotlin.ir.declarations.lazy.AbstractIrLazyFunction
import org.jetbrains.kotlin.ir.declarations.lazy.lazyVar
import org.jetbrains.kotlin.ir.expressions.IrBody
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.util.DeclarationStubGenerator
import org.jetbrains.kotlin.ir.util.TypeTranslator
import org.jetbrains.kotlin.ir.util.withScope
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.descriptorUtil.propertyIfAccessor
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DescriptorWithContainerSource
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource

@OptIn(ObsoleteDescriptorBasedAPI::class)
class IrLazyFunction(
    override var startOffset: Int,
    override var endOffset: Int,
    override var origin: IrDeclarationOrigin,
    override val symbol: IrSimpleFunctionSymbol,
    override val descriptor: FunctionDescriptor,
    override var name: Name,
    override var visibility: DescriptorVisibility,
    override var modality: Modality,
    override var isInline: Boolean,
    override var isExternal: Boolean,
    override var isTailrec: Boolean,
    override var isSuspend: Boolean,
    override var isExpect: Boolean,
    override var isFakeOverride: Boolean,
    override var isOperator: Boolean,
    override var isInfix: Boolean,
    override val stubGenerator: DeclarationStubGenerator,
    override val typeTranslator: TypeTranslator,
) : AbstractIrLazyFunction(), Psi2IrLazyFunctionBase {
    override var annotations: List<IrConstructorCall> by createLazyAnnotations()

    override var body: IrBody? by lazyVar(stubGenerator.lock) {
        if (tryLoadIr()) body else null
    }

    override var returnType: IrType by lazyVar(stubGenerator.lock) {
        createReturnType()
    }

    override val initialSignatureFunction: IrFunction? by createInitialSignatureFunction()

    override var metadata: MetadataSource?
        get() = null
        set(_) = error("We should never need to store metadata of external declarations.")

    override var typeParameters: List<IrTypeParameter> by lazyVar(stubGenerator.lock) {
        typeTranslator.buildWithScope(this) {
            stubGenerator.symbolTable.withScope(this) {
                val propertyIfAccessor = descriptor.propertyIfAccessor
                propertyIfAccessor.typeParameters.mapTo(arrayListOf()) { typeParameterDescriptor ->
                    if (descriptor != propertyIfAccessor) {
                        stubGenerator.generateOrGetScopedTypeParameterStub(typeParameterDescriptor).also { irTypeParameter ->
                            irTypeParameter.parent = this@IrLazyFunction
                        }
                    } else {
                        stubGenerator.generateOrGetTypeParameterStub(typeParameterDescriptor)
                    }
                }
            }
        }
    }

    override var overriddenSymbols: List<IrSimpleFunctionSymbol> by lazyVar(stubGenerator.lock) {
        descriptor.overriddenDescriptors.mapTo(arrayListOf()) {
            stubGenerator.generateFunctionStub(it.original).symbol
        }
    }

    override var attributeOwnerId: IrElement = this

    override var correspondingPropertySymbol: IrPropertySymbol? = null

    override val containerSource: DeserializedContainerSource?
        get() = (descriptor as? DescriptorWithContainerSource)?.containerSource

    override val isDeserializationEnabled: Boolean
        get() = stubGenerator.extensions.irDeserializationEnabled

    init {
        symbol.bind(this)
    }
}
