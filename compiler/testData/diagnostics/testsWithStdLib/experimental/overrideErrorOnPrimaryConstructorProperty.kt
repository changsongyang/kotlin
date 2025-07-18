// RUN_PIPELINE_TILL: FRONTEND
// ISSUE: KT-37786

@RequiresOptIn
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.PROPERTY)
annotation class Experimental

interface Foo {
    @Experimental
    val foo: Int
}

data class Bar @Experimental constructor(override val foo: Int): Foo

fun main() {
    @OptIn(Experimental::class)
    val bar = Bar(42)

    bar.foo
}

/* GENERATED_FIR_TAGS: annotationDeclaration, classDeclaration, classReference, data, functionDeclaration,
integerLiteral, interfaceDeclaration, localProperty, override, primaryConstructor, propertyDeclaration */
