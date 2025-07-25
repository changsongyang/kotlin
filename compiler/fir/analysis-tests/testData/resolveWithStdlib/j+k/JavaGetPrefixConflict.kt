// RUN_PIPELINE_TILL: BACKEND
// FILE: JavaClass.java

public class JavaClass {
    public String getText() {
        return "Text";
    }

    public String getText(String param) {
        return "Text with " + param;
    }
}

// FILE: Test.kt

fun test() {
    val jc = JavaClass()
    val result = jc.text
}

fun otherTest(jc: JavaClass) {
    val result = jc.text
}

/* GENERATED_FIR_TAGS: flexibleType, functionDeclaration, javaFunction, javaProperty, javaType, localProperty,
propertyDeclaration */
