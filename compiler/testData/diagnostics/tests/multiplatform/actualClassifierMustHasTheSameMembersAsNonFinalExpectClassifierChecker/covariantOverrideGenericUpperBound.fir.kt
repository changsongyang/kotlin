// IGNORE_FIR_DIAGNOSTICS
// RUN_PIPELINE_TILL: BACKEND
// MODULE: m1-common
// FILE: common.kt

interface I

open class Base {
    open fun foo(): I = null!!
}

expect open class Foo<T : I> : Base {
}

// MODULE: m2-jvm()()(m1-common)
// FILE: jvm.kt

actual open class Foo<T : I> : Base() {
    override fun <!EXPECT_ACTUAL_INCOMPATIBLE_RETURN_TYPE!>foo<!>(): T = null!!
}

/* GENERATED_FIR_TAGS: actual, checkNotNullCall, classDeclaration, expect, functionDeclaration, interfaceDeclaration,
override, typeConstraint, typeParameter */
