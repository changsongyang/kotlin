// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
fun test() {
    class A {
        <!WRONG_MODIFIER_CONTAINING_DECLARATION!>companion<!> object {}
    }

    object {
        <!WRONG_MODIFIER_CONTAINING_DECLARATION!>companion<!> object {}
    }
}

/* GENERATED_FIR_TAGS: anonymousObjectExpression, classDeclaration, companionObject, functionDeclaration, localClass,
objectDeclaration */
