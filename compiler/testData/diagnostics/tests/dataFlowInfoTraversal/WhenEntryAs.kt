// RUN_PIPELINE_TILL: FRONTEND
// CHECK_TYPE

fun foo(x: Number, y: Int) {
    when (x) {
        x as Int -> checkSubtype<Int>(<!DEBUG_INFO_SMARTCAST!>x<!>)
        y -> {}
        else -> {}
    }
    checkSubtype<Int>(<!DEBUG_INFO_SMARTCAST!>x<!>)
}

fun bar(x: Number) {
    when (x) {
        x as Int -> checkSubtype<Int>(<!DEBUG_INFO_SMARTCAST!>x<!>)
        else -> {}
    }
    checkSubtype<Int>(<!DEBUG_INFO_SMARTCAST!>x<!>)
}

fun whenWithoutSubject(x: Number) {
    when {
        (x as Int) == 42 -> checkSubtype<Int>(<!DEBUG_INFO_SMARTCAST!>x<!>)
        else -> {}
    }
    checkSubtype<Int>(<!DEBUG_INFO_SMARTCAST!>x<!>)
}

/* GENERATED_FIR_TAGS: asExpression, classDeclaration, equalityExpression, funWithExtensionReceiver, functionDeclaration,
functionalType, infix, integerLiteral, nullableType, smartcast, typeParameter, typeWithExtension, whenExpression,
whenWithSubject */
