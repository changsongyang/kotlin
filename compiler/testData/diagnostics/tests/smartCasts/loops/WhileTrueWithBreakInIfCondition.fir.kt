// RUN_PIPELINE_TILL: FRONTEND
fun checkJump(x: Int?, y: Int?) {
    while (true) {
        if (x ?: break == 0) {
            y!!
        } else {
            y!!
        }
        // Ok
        y.hashCode()
    }
    // Smart cast here is erroneous: y is nullable
    y<!UNSAFE_CALL!>.<!>hashCode()
}

/* GENERATED_FIR_TAGS: break, checkNotNullCall, elvisExpression, equalityExpression, functionDeclaration, ifExpression,
integerLiteral, nullableType, smartcast, whileLoop */
