// RUN_PIPELINE_TILL: FRONTEND
// ISSUE: KT-70756

operator fun <T> String.invoke(t: T) {}

fun main() {
    <!FUNCTION_EXPECTED!>8<!> {
        {
            1
        }
    }
}

/* GENERATED_FIR_TAGS: funWithExtensionReceiver, functionDeclaration, integerLiteral, lambdaLiteral, nullableType,
operator, typeParameter */
