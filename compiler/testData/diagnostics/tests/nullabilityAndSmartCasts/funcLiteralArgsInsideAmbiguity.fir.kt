// RUN_PIPELINE_TILL: FRONTEND
// DIAGNOSTICS: -UNUSED_PARAMETER -UNUSED_VARIABLE
package d

fun bar() {
    val i: Int? = 42
    if (i != null) {
        <!OVERLOAD_RESOLUTION_AMBIGUITY!>doSmth1<!> {
        val x = i + 1
    }
}
}

fun doSmth1(f: ()->Unit) {}
fun doSmth1(g: (Int)->Unit) {}

/* GENERATED_FIR_TAGS: additiveExpression, equalityExpression, functionDeclaration, functionalType, ifExpression,
integerLiteral, lambdaLiteral, localProperty, nullableType, propertyDeclaration, smartcast */
