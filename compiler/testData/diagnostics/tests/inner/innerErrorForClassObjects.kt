// RUN_PIPELINE_TILL: FRONTEND
open class SomeClass<T>
class TestSome<P> {
    companion object : SomeClass<<!UNRESOLVED_REFERENCE!>P<!>>() {
    }
}

class Test {
    companion object : <!DEBUG_INFO_UNRESOLVED_WITH_TARGET, UNRESOLVED_REFERENCE!>InnerClass<!>() {
        val a = object: <!DEBUG_INFO_UNRESOLVED_WITH_TARGET, UNRESOLVED_REFERENCE!>InnerClass<!>() {
        }

        fun more(): InnerClass {
            val b = <!RESOLUTION_TO_CLASSIFIER!>InnerClass<!>()

            val testVal = <!UNRESOLVED_REFERENCE!>inClass<!>
            <!UNRESOLVED_REFERENCE!>foo<!>()

            return <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>b<!>
        }
    }

    val inClass = 12
    fun foo() {}

    open inner class InnerClass
}

/* GENERATED_FIR_TAGS: anonymousObjectExpression, classDeclaration, companionObject, functionDeclaration, inner,
integerLiteral, localProperty, nullableType, objectDeclaration, propertyDeclaration, typeParameter */
