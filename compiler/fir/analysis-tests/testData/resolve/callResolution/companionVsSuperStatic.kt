// RUN_PIPELINE_TILL: BACKEND
// FILE: JavaClass.java

public class JavaClass {
    public static String FIELD = "";
}

// FILE: main.kt

class A : JavaClass() {
    companion object {
        val FIELD = 1
    }

    fun foo() {
        <!DEBUG_INFO_EXPRESSION_TYPE("kotlin.Int")!>FIELD<!>
    }
}

/* GENERATED_FIR_TAGS: classDeclaration, companionObject, functionDeclaration, integerLiteral, javaType,
objectDeclaration, propertyDeclaration */
