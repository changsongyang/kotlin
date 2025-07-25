// RUN_PIPELINE_TILL: FRONTEND
class A(val next: A? = null) {
    <!MUST_BE_INITIALIZED_OR_BE_ABSTRACT!>val x: String<!>
    init {
        next?.<!VAL_REASSIGNMENT!>x<!> = "a"
    }
}

class B(val next: B? = null) {
    var x: String = next?.x ?: "default" // it's ok to use `x` of next
}

/* GENERATED_FIR_TAGS: assignment, classDeclaration, elvisExpression, init, nullableType, primaryConstructor,
propertyDeclaration, safeCall, stringLiteral */
