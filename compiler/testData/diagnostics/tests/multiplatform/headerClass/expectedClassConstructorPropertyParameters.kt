// FIR_IDENTICAL
// IGNORE_FIR_DIAGNOSTICS
// RUN_PIPELINE_TILL: BACKEND
// DIAGNOSTICS: -NO_ACTUAL_FOR_EXPECT
// MODULE: m1-common
// FILE: common.kt
expect class Foo(
        <!EXPECTED_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER!>val constructorProperty: String<!>,
        <!EXPECTED_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER!>var constructorVar: String<!>,
        constructorParameter: String,
) {
    constructor(<!VAL_OR_VAR_ON_SECONDARY_CONSTRUCTOR_PARAMETER!>val<!> notReported: String)
}

expect annotation class Ann(val s: String)

expect value class ValueClass(val s: String)

/* GENERATED_FIR_TAGS: annotationDeclaration, classDeclaration, expect, primaryConstructor, propertyDeclaration,
secondaryConstructor, value */
