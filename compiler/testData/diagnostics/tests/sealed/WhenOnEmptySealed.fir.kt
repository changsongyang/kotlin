// RUN_PIPELINE_TILL: FRONTEND
// DIAGNOSTICS: -UNUSED_EXPRESSION
sealed class Sealed {

}

fun foo(s: Sealed): Int {
    return <!NO_ELSE_IN_WHEN!>when<!>(s) {
        // We do not return anything, so else branch must be here
    }
}

/* GENERATED_FIR_TAGS: classDeclaration, functionDeclaration, sealed, whenExpression, whenWithSubject */
