// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// OPT_IN: kotlin.RequiresOptIn
// FILE: api.kt

package api

@RequiresOptIn
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class ExperimentalAPI

@ExperimentalAPI
class Foo

typealias Bar = <!OPT_IN_USAGE_ERROR!>Foo<!>

/* GENERATED_FIR_TAGS: annotationDeclaration, classDeclaration, typeAliasDeclaration */
