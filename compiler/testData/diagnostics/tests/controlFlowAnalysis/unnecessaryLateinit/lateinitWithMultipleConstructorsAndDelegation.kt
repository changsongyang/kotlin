// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_PARAMETER

class Foo {
    <!UNNECESSARY_LATEINIT!>lateinit<!> var bar: String

    constructor() {
        bar = ""
    }

    constructor(a: Int) : this() {
    }

    constructor(a: Int, b: Int) : this(a) {
    }
}

/* GENERATED_FIR_TAGS: assignment, classDeclaration, lateinit, propertyDeclaration, secondaryConstructor, stringLiteral */
