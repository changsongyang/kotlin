// RUN_PIPELINE_TILL: FRONTEND
// LANGUAGE: +WarnAboutNonExhaustiveWhenOnAlgebraicTypes
// See also: KT-11998
data class My(val x: Boolean?)

fun doIt() {}

fun foo(my: My) {
    if (my.x != null) {
        // my.x should be smart-cast
        if (<!DEBUG_INFO_SMARTCAST!>my.x<!>) doIt()
        <!NO_ELSE_IN_WHEN!>when<!> (<!DEBUG_INFO_SMARTCAST!>my.x<!>) {
            true -> doIt()
        }
        when {
            <!DEBUG_INFO_SMARTCAST!>my.x<!> -> doIt()
        }
    }
}

fun bar(x: Boolean?) {
    if (x != null) {
        // x should be smart-cast
        if (<!DEBUG_INFO_SMARTCAST!>x<!>) doIt()
        <!NO_ELSE_IN_WHEN!>when<!> (<!DEBUG_INFO_SMARTCAST!>x<!>) {
            true -> doIt()
        }
        when {
            <!DEBUG_INFO_SMARTCAST!>x<!> -> doIt()
        }
    }
}

/* GENERATED_FIR_TAGS: classDeclaration, data, equalityExpression, functionDeclaration, ifExpression, nullableType,
primaryConstructor, propertyDeclaration, smartcast, whenExpression, whenWithSubject */
