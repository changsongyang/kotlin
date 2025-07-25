// RUN_PIPELINE_TILL: FRONTEND
// ISSUE: KT-44511, also relates to KT-8492 and KT-26357

class A(val b: Boolean) {
    fun foo() {}
}

fun test_1(a: A?) {
    if (a?.b ?: false) {
        a.foo() // OK
    } else {
        a<!UNSAFE_CALL!>.<!>foo() // Error
    }
}

fun test_2(a: A?) {
    if (a?.b ?: true) {
        a<!UNSAFE_CALL!>.<!>foo() // Error
    } else {
        a.foo() // OK
    }
}

/* GENERATED_FIR_TAGS: classDeclaration, elvisExpression, functionDeclaration, ifExpression, nullableType,
primaryConstructor, propertyDeclaration, safeCall, smartcast */
