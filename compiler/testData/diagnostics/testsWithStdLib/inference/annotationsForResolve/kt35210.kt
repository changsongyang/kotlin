// RUN_PIPELINE_TILL: BACKEND
// DIAGNOSTICS: -UNUSED_PARAMETER

@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

annotation class Anno

fun test(a: List<Class<Anno>>) {
    strictSelect(a, emptyList<Anno>().map { it.annotationClass.java })
}

fun <@kotlin.internal.OnlyInputTypes S> strictSelect(arg1: S, arg2: S): S = TODO()

/* GENERATED_FIR_TAGS: annotationDeclaration, annotationUseSiteTargetFile, capturedType, functionDeclaration,
lambdaLiteral, nullableType, outProjection, stringLiteral, typeParameter */
