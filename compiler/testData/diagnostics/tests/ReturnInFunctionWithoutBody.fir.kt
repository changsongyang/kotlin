// RUN_PIPELINE_TILL: FRONTEND
// LATEST_LV_DIFFERENCE
fun foo1(): () -> String = <!RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!> { "some long expression "}
fun foo2(): () -> String = return<!UNRESOLVED_LABEL!>@label<!> { "some long expression "}
fun foo3(): () -> String = <!RETURN_IN_FUNCTION_WITH_EXPRESSION_BODY!>return<!><!SYNTAX!>@<!> { "some long expression "}

/* GENERATED_FIR_TAGS: functionDeclaration, functionalType, lambdaLiteral, stringLiteral */
