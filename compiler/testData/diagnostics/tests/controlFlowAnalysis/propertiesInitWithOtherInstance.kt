// RUN_PIPELINE_TILL: FRONTEND
class A(val next: A? = null) {
    val x: String
    init {
        <!VAL_REASSIGNMENT!>next?.x<!> = "a"
        x = "b"
        <!VAL_REASSIGNMENT!>this.x<!> = "c"
        x = "d" // don't repeat the same diagnostic again with this receiver
        this.x = "e"

        <!VAL_REASSIGNMENT!>next?.x<!> = "f"
    }
}

/* GENERATED_FIR_TAGS: assignment, classDeclaration, init, nullableType, primaryConstructor, propertyDeclaration,
safeCall, stringLiteral, thisExpression */
