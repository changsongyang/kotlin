// RUN_PIPELINE_TILL: BACKEND
// FILE: A.java
public class A {
    public String x = "";
}

// FILE: B.java
public class B extends A {
    public int x = 1;
}

// FILE: main.kt

fun main(b: B) {
    b.x + 1
}

/* GENERATED_FIR_TAGS: additiveExpression, functionDeclaration, integerLiteral, javaProperty, javaType */
