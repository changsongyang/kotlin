// IGNORE_FIR_DIAGNOSTICS
// RUN_PIPELINE_TILL: BACKEND
// MODULE: m1-common
// FILE: jvm.kt

actual fun <!ACTUAL_WITHOUT_EXPECT, ACTUAL_WITHOUT_EXPECT{METADATA}!>foo<!>() { }

// MODULE: m1-jvm()()(m1-common)

/* GENERATED_FIR_TAGS: actual, functionDeclaration */
