// RUN_PIPELINE_TILL: FRONTEND
class A<T1, T2> {
    inner class B<K1, K2> {
        inner class C<U1, U2>

        fun <R3> foo(c: C<R3, R3>) {}
    }

    fun <R2, R3> foo(c: B<R2, R2>.C<R3, R3>) {}
}

fun <R1, R2, R3> foo(c: A<R1, R1>.B<R2, R2>.C<R3, R3>) {}

fun <R3> foo(c: A.<!WRONG_NUMBER_OF_TYPE_ARGUMENTS!>B<!>.C<R3, R3>) {}

/* GENERATED_FIR_TAGS: classDeclaration, functionDeclaration, inner, nullableType, typeParameter */
