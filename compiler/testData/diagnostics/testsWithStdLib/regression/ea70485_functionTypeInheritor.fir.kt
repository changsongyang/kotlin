// RUN_PIPELINE_TILL: FRONTEND
class O : Function2<Int, String, Unit> {
    override fun invoke(p1: Int, p2: String) {
    }
}

fun test() {
    val a = fun(o: O) {
    }
    a <!ARGUMENT_TYPE_MISMATCH!>{}<!>
}

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class Ext<!> : <!SUPERTYPE_IS_EXTENSION_OR_CONTEXT_FUNCTION_TYPE!>String.() -> Unit<!> {
}

fun test2() {
    val f: Ext = <!INITIALIZER_TYPE_MISMATCH!>{}<!>
}

/* GENERATED_FIR_TAGS: anonymousFunction, classDeclaration, functionDeclaration, functionalType, lambdaLiteral,
localProperty, operator, override, propertyDeclaration, typeWithExtension */
