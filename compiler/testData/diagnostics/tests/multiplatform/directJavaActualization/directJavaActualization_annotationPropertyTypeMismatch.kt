// RUN_PIPELINE_TILL: FIR2IR
// WITH_KOTLIN_JVM_ANNOTATIONS
// LANGUAGE:+DirectJavaActualization
// WITH_STDLIB

// MODULE: m1-common
// FILE: common.kt
expect annotation class <!IMPLICIT_JVM_ACTUALIZATION{JVM}!>Foo<!>(val foo: Int)

// MODULE: m2-jvm()()(m1-common)
// FILE: Foo.java
@kotlin.annotations.jvm.KotlinActual public @interface Foo {
    @kotlin.annotations.jvm.KotlinActual String foo();
}

/* GENERATED_FIR_TAGS: annotationDeclaration, expect, primaryConstructor, propertyDeclaration */
