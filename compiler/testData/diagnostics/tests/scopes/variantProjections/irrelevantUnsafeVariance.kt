// RUN_PIPELINE_TILL: FRONTEND
// ISSUE: KT-57609

interface A<T> {
    fun foo(x: @UnsafeVariance T)
}

fun foo(a1: A<out Any?>, a2: A<*>) {
    a1.foo("")
    a2.foo("")
}

/* GENERATED_FIR_TAGS: functionDeclaration, interfaceDeclaration, nullableType, outProjection, starProjection,
stringLiteral, typeParameter */
