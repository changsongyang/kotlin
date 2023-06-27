/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.resolve.calls.mpp

import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker
import org.jetbrains.kotlin.mpp.TypeAliasSymbolMarker
import org.jetbrains.kotlin.name.StandardClassIds

object AbstractExpectActualAnnotationMatchChecker {
    private val SKIPPED_CLASS_IDS = setOf(
        StandardClassIds.Annotations.Deprecated,
        StandardClassIds.Annotations.DeprecatedSinceKotlin,
        StandardClassIds.Annotations.OptionalExpectation,
        StandardClassIds.Annotations.RequireKotlin,
        StandardClassIds.Annotations.SinceKotlin,
        StandardClassIds.Annotations.Suppress,
        StandardClassIds.Annotations.WasExperimental,
    )

    class Incompatibility(val expectSymbol: DeclarationSymbolMarker, val actualSymbol: DeclarationSymbolMarker)

    fun areAnnotationsCompatible(
        expectSymbol: DeclarationSymbolMarker,
        actualSymbol: DeclarationSymbolMarker,
        context: ExpectActualMatchingContext<*>,
    ): Incompatibility? = with(context) { areAnnotationsCompatible(expectSymbol, actualSymbol) }

    context (ExpectActualMatchingContext<*>)
    private fun areAnnotationsCompatible(
        expectSymbol: DeclarationSymbolMarker,
        actualSymbol: DeclarationSymbolMarker,
    ): Incompatibility? {
        if (actualSymbol is TypeAliasSymbolMarker) {
            val expanded = actualSymbol.expandToRegularClass() ?: return null
            return areAnnotationsCompatible(expectSymbol, expanded)
        }
        // TODO(Roman.Efremov, KT-58551): properly handle repeatable annotations
        // TODO(Roman.Efremov, KT-58551): check other annotation targets (constructors, types, value parameters, etc)
        // TODO(Roman.Efremov, KT-58551): fix actual typealias class members not checked in FE checkers
        // TODO(Roman.Efremov, KT-58551): check annotations on fake overrides in case of implicit actualization

        val skipSourceAnnotations = !actualSymbol.hasSourceAnnotationsErased
        val actualAnnotationsByName = actualSymbol.annotations.groupBy { it.classId }

        for (expectAnnotation in expectSymbol.annotations) {
            if (expectAnnotation.classId in SKIPPED_CLASS_IDS || expectAnnotation.isOptIn) {
                continue
            }
            if (expectAnnotation.isRetentionSource && skipSourceAnnotations) {
                continue
            }
            val actualAnnotationsWithSameClassId = actualAnnotationsByName[expectAnnotation.classId] ?: emptyList()
            if (actualAnnotationsWithSameClassId.none { areAnnotationArgumentsEqual(expectAnnotation, it) }) {
                return Incompatibility(expectSymbol, actualSymbol)
            }
        }
        return null
    }
}