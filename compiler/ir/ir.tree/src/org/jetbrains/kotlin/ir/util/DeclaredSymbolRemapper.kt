/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.util

import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.IrReturnableBlock
import org.jetbrains.kotlin.ir.symbols.*

/**
 * Used to replace declarations' own symbols with new ones.
 */
interface DeclaredSymbolRemapper {

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrValueParameter.symbol]
     */
    fun getDeclaredValueParameter(symbol: IrValueParameterSymbol): IrValueParameterSymbol

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrClass.symbol]
     */
    fun getDeclaredClass(symbol: IrClassSymbol): IrClassSymbol

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrAnonymousInitializer.symbol]
     */
    fun getDeclaredAnonymousInitializer(symbol: IrAnonymousInitializerSymbol): IrAnonymousInitializerSymbol

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrTypeParameter.symbol]
     */
    fun getDeclaredTypeParameter(symbol: IrTypeParameterSymbol): IrTypeParameterSymbol

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrConstructor.symbol]
     */
    fun getDeclaredConstructor(symbol: IrConstructorSymbol): IrConstructorSymbol

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrEnumEntry.symbol]
     */
    fun getDeclaredEnumEntry(symbol: IrEnumEntrySymbol): IrEnumEntrySymbol

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrFunctionWithLateBinding.symbol]
     * - [IrSimpleFunction.symbol]
     */
    fun getDeclaredSimpleFunction(symbol: IrSimpleFunctionSymbol): IrSimpleFunctionSymbol

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrPropertyWithLateBinding.symbol]
     * - [IrProperty.symbol]
     */
    fun getDeclaredProperty(symbol: IrPropertySymbol): IrPropertySymbol

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrField.symbol]
     */
    fun getDeclaredField(symbol: IrFieldSymbol): IrFieldSymbol

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrLocalDelegatedProperty.symbol]
     */
    fun getDeclaredLocalDelegatedProperty(symbol: IrLocalDelegatedPropertySymbol): IrLocalDelegatedPropertySymbol

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrScript.symbol]
     */
    fun getDeclaredScript(symbol: IrScriptSymbol): IrScriptSymbol

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrTypeAlias.symbol]
     */
    fun getDeclaredTypeAlias(symbol: IrTypeAliasSymbol): IrTypeAliasSymbol

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrVariable.symbol]
     */
    fun getDeclaredVariable(symbol: IrVariableSymbol): IrVariableSymbol

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrExternalPackageFragment.symbol]
     */
    fun getDeclaredExternalPackageFragment(symbol: IrExternalPackageFragmentSymbol): IrExternalPackageFragmentSymbol

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrFile.symbol]
     */
    fun getDeclaredFile(symbol: IrFileSymbol): IrFileSymbol

    /**
     * Remaps symbols stored, e.g., in the following properties (not necessarily limited to those properties):
     * - [IrReturnableBlock.symbol]
     */
    fun getDeclaredReturnableBlock(symbol: IrReturnableBlockSymbol): IrReturnableBlockSymbol
}
