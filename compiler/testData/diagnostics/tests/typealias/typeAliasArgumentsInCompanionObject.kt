// RUN_PIPELINE_TILL: FRONTEND
class C<T1, T2> {
    companion object {
        val OK = "OK"
    }
}

typealias C2<T> = C<T, T>

val test1: String = <!FUNCTION_CALL_EXPECTED!>C2<String><!>.<!UNRESOLVED_REFERENCE!>OK<!>
val test2: String = C2.OK

/* GENERATED_FIR_TAGS: classDeclaration, companionObject, nullableType, objectDeclaration, propertyDeclaration,
stringLiteral, typeAliasDeclaration, typeAliasDeclarationWithTypeParameter, typeParameter */
