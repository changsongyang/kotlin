// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
abstract class A {
    abstract var x: Int;
    abstract fun foo() : Int;
}

abstract class C : A() {
    override abstract var x: <!VAR_TYPE_MISMATCH_ON_OVERRIDE!>String<!> =<!SYNTAX!><!> <!SYNTAX!>?<!>
    override abstract fun foo(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>String<!> =<!SYNTAX!><!> <!SYNTAX!>?<!>
}

/* GENERATED_FIR_TAGS: classDeclaration, functionDeclaration, override, propertyDeclaration */
