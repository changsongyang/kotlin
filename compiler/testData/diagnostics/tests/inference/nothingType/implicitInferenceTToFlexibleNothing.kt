// RUN_PIPELINE_TILL: FRONTEND
// DIAGNOSTICS: -UNUSED_VARIABLE -UNCHECKED_CAST -UNUSED_PARAMETER
// SKIP_TXT

import java.util.*

fun <T> foo (f: () -> List<T>): T = null as T

fun main() {
    val x = <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>foo<!> { Collections.<!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>emptyList<!>() }
}

/* GENERATED_FIR_TAGS: asExpression, flexibleType, functionDeclaration, functionalType, javaFunction, lambdaLiteral,
localProperty, nullableType, propertyDeclaration, typeParameter */
