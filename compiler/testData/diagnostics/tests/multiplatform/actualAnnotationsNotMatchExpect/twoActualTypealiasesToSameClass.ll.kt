// LL_FIR_DIVERGENCE
// Not a real LL divergence, it's just tiered runners reporting errors from `BACKEND`
// LL_FIR_DIVERGENCE
// RUN_PIPELINE_TILL: BACKEND
// MODULE: m1-common
// FILE: common.kt
annotation class Ann

expect class WithAnn {
    @Ann
    fun foo()
}

expect class WithoutAnn {
    fun foo()
}

// MODULE: m1-jvm()()(m1-common)
// FILE: jvm.kt
class Impl {
    fun foo() {}
}

actual typealias <!ACTUAL_ANNOTATIONS_NOT_MATCH_EXPECT!>WithAnn<!> = Impl
actual typealias WithoutAnn = Impl

/* GENERATED_FIR_TAGS: actual, annotationDeclaration, classDeclaration, expect, functionDeclaration,
typeAliasDeclaration */
