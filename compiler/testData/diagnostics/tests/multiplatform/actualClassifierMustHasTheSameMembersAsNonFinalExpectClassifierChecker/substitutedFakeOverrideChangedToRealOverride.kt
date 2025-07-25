// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// MODULE: m1-common
// FILE: common.kt

open class Base<T> {
    open fun foo(t: T) {}
}

expect open class Foo : Base<String>

// MODULE: m2-jvm()()(m1-common)
// FILE: jvm.kt

actual open class Foo : Base<String>() {
    override fun foo(t: String) {}
}

/* GENERATED_FIR_TAGS: actual, classDeclaration, expect, functionDeclaration, nullableType, override, typeParameter */
