// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
@Target(AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
annotation class ExprAnn

@Target(AnnotationTarget.FUNCTION)
annotation class FunAnn

fun foo(): Int {
    val x = @ExprAnn fun() = 1
    val y = @FunAnn fun() = 2
    return x() + y()    
}

/* GENERATED_FIR_TAGS: additiveExpression, annotationDeclaration, anonymousFunction, functionDeclaration, integerLiteral,
localProperty, propertyDeclaration */
