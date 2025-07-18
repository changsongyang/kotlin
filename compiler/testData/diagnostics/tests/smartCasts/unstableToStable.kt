// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
class Foo(var x: Int?) {
    init {
        if (x != null) {
            val y = x
            // Error: x is not stable, Type(y) = Int?
            <!SMARTCAST_IMPOSSIBLE!>x<!>.hashCode()
            y<!UNSAFE_CALL!>.<!>hashCode()
            if (y == x) {
                // Still error
                y<!UNSAFE_CALL!>.<!>hashCode()
            }
            if (x == null && y != x) {
                // Still error
                y<!UNSAFE_CALL!>.<!>hashCode()
            }
        }
    }
}

/* GENERATED_FIR_TAGS: andExpression, classDeclaration, equalityExpression, ifExpression, init, localProperty,
nullableType, primaryConstructor, propertyDeclaration, smartcast */
