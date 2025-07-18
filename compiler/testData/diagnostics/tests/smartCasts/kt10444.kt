// RUN_PIPELINE_TILL: BACKEND
// DIAGNOSTICS: -UNUSED_PARAMETER
// KT-10444 Do not ignore smart (unchecked) casts to the same classifier

class Base<in T>
class Qwe<T : Any>(val a: T?) {
    fun test1(obj: Any) {
        obj <!UNCHECKED_CAST!>as Qwe<T><!>
        check(<!DEBUG_INFO_SMARTCAST!>obj<!>.a)
    }

    fun test1(obj: Qwe<*>) {
        obj <!UNCHECKED_CAST!>as Qwe<T><!>
        check(obj.a)
    }

    fun test2(b: Base<*>) {
        b <!UNCHECKED_CAST!>as Base<Any><!>
    }

    fun check(a: T?) {
    }
}

open class Foo
open class Bar<T: Foo>(open val a: T?, open val b: T?) {
    @Suppress("UNCHECKED_CAST")
    fun compare(obj: Any) {
        if (obj !is Bar<*>) {
            throw IllegalArgumentException()
        }
        if (System.currentTimeMillis() > 100) {
            val b = (obj as Bar<T>).b
            if (b == null) throw IllegalArgumentException()
            check(<!DEBUG_INFO_SMARTCAST!>obj<!>.a, <!DEBUG_INFO_SMARTCAST!>b<!>)
        }
    }
    fun check(a: T?, b: T) {
    }
}

/* GENERATED_FIR_TAGS: asExpression, classDeclaration, comparisonExpression, dnnType, equalityExpression,
functionDeclaration, ifExpression, in, integerLiteral, isExpression, javaFunction, localProperty, nullableType,
primaryConstructor, propertyDeclaration, smartcast, starProjection, stringLiteral, typeConstraint, typeParameter */
