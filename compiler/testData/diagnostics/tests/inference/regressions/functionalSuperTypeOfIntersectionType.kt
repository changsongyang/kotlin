// RUN_PIPELINE_TILL: FRONTEND
// ISSUE: KT-73095
open class A<Y: Function0<Any>>(val f1: Y, val f2: Y) {
    fun test() {
        val x: Float = 1.0f
        var func1: () -> LongArray = <!VARIABLE_WITH_REDUNDANT_INITIALIZER!>{ -> LongArray(0)}<!>
        var func2: Function0<Function1<Any, <!REDUNDANT_PROJECTION!>out<!> CharArray>> = fun (): Function1<Any, <!REDUNDANT_PROJECTION!>out<!> CharArray> {
            return { x -> CharArray(0) }
        }
        func1 = <!TYPE_MISMATCH!>func2<!>
        A<Function0<Any>>({ -> x}, func1)
    }
}

/* GENERATED_FIR_TAGS: anonymousFunction, assignment, classDeclaration, functionDeclaration, functionalType,
integerLiteral, intersectionType, lambdaLiteral, localProperty, outProjection, primaryConstructor, propertyDeclaration,
smartcast, typeConstraint, typeParameter */
