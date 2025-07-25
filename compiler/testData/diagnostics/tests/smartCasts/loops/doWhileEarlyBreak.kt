// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
fun x(): Boolean { return true }

fun y(): Boolean { return false }

public fun foo(p: String?): Int {
    do {
        if (y()) break
        // We do not always reach this statement
        p!!.length
    } while (!x())
    // Here we have do while loop but p is still nullable due to break before
    return p<!UNSAFE_CALL!>.<!>length
}

/* GENERATED_FIR_TAGS: break, checkNotNullCall, doWhileLoop, functionDeclaration, ifExpression, nullableType */
