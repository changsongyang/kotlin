// RUN_PIPELINE_TILL: FRONTEND
package kt606_dependents

//KT-1489 Code analyzer fails with assertion
interface AutoCloseable{
    fun close()
}

class C {
    class Resource : AutoCloseable {
        override fun close() {
            throw UnsupportedOperationException()
        }
    }

    fun <X : AutoCloseable> foo(x : X, body : (X) -> Unit) {
    }

    fun p() : Resource? = null

    fun bar() {
        foo(<!TYPE_MISMATCH!>p()<!>) {

        }
    }
}

//KT-1728 Can't invoke extension property as a function

val Int.ext : () -> Int get() = { 5 }
val x = 1.ext()

/* GENERATED_FIR_TAGS: classDeclaration, functionDeclaration, functionalType, getter, integerLiteral,
interfaceDeclaration, lambdaLiteral, nestedClass, nullableType, override, propertyDeclaration,
propertyWithExtensionReceiver, typeConstraint, typeParameter */
