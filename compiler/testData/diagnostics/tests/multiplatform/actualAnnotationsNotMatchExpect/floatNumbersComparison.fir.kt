// RUN_PIPELINE_TILL: BACKEND
// MODULE: m1-common
// FILE: common.kt
annotation class Ann(val p: Double)

@Ann(0.3)
expect fun floatNumbersComparison()

// MODULE: m1-jvm()()(m1-common)
// FILE: jvm.kt
@Ann(0.1 + 0.1 + 0.1)
<!ACTUAL_ANNOTATIONS_NOT_MATCH_EXPECT!>actual<!> fun floatNumbersComparison() {}

/* GENERATED_FIR_TAGS: actual, additiveExpression, annotationDeclaration, expect, functionDeclaration,
primaryConstructor, propertyDeclaration */
