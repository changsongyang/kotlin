// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
// Check that there won't be "Rewrite at slice ANNOTATION key" exception - EA-36935
@<!UNRESOLVED_REFERENCE!>someErrorAnnotation<!> object Test {
}

/* GENERATED_FIR_TAGS: objectDeclaration */
