// RUN_PIPELINE_TILL: BACKEND
fun <T> id(t: T) = t


fun main() {
    val a = id("string")
    val b = id(null)
    val c = id(id(a))
}

/* GENERATED_FIR_TAGS: functionDeclaration, localProperty, nullableType, propertyDeclaration, stringLiteral,
typeParameter */
