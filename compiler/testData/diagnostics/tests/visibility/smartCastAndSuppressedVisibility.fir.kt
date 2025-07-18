// RUN_PIPELINE_TILL: BACKEND
// DISABLE_IR_VISIBILITY_CHECKS: JVM_IR
// SKIP_TXT
// ISSUE: KT-55024
// MODULE: a
interface A {
    fun foo()
}

internal sealed class B(val x: A) : A {
    override fun foo() {}
    fun bar() {}
}

// MODULE: b(a)
@Suppress(<!ERROR_SUPPRESSION!>"INVISIBLE_REFERENCE"<!>, "INVISIBLE_MEMBER")
private fun test_1(x: A) {
    if (x is B) {
        x.foo()
        x.bar()
    }
}

/* GENERATED_FIR_TAGS: classDeclaration, functionDeclaration, ifExpression, interfaceDeclaration, isExpression, override,
primaryConstructor, propertyDeclaration, sealed, smartcast, stringLiteral */
