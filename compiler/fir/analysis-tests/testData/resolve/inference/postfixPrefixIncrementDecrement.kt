// RUN_PIPELINE_TILL: BACKEND
fun prefixIncrement() {
    val intArray = IntArray(10)
    val a1 = Array(10) { i -> { ++intArray[i] } }
    var x = 0
    val a2 = Array(10) { i -> { ++x } }
}

fun prefixDecrement() {
    val intArray = IntArray(10)
    val a1 = Array(10) { i -> { --intArray[i] } }
    var x = 0
    val a2 = Array(10) { i -> { --x } }
}

fun postfixIncrement() {
    val intArray = IntArray(10)
    val a1 = Array(10) { i -> { intArray[i]++ } }
    var x = 0
    val a2 = Array(10) { i -> { x++ } }
}

fun postfixDecrement() {
    val intArray = IntArray(10)
    val a1 = Array(10) { i -> { intArray[i]-- } }
    var x = 0
    val a2 = Array(10) { i -> { x-- } }
}

/* GENERATED_FIR_TAGS: assignment, functionDeclaration, incrementDecrementExpression, integerLiteral, lambdaLiteral,
localProperty, propertyDeclaration */
