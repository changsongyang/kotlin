// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
fun <T> listOf(): List<T> = null!!
fun <T> listOf(vararg values: T): List<T> = null!!

val flag = true

val a: () -> List<Int> = l@ {
    if (flag) return@l listOf()
    listOf(5)
}

/* GENERATED_FIR_TAGS: checkNotNullCall, functionDeclaration, functionalType, ifExpression, integerLiteral,
lambdaLiteral, nullableType, propertyDeclaration, typeParameter, vararg */
