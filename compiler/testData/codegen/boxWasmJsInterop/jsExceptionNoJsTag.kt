// TARGET_BACKEND: WASM
// WASM_NO_JS_TAG

fun throwSomeJsException(): Int = js("{ throw new TypeError('Test'); }")
fun throwSomeJsPrimitive(): Int = js("{ throw 'Test'; }")
fun throwSomeKotlinException(): Int = throw IllegalStateException("Test")
fun throwNumberFromJs(): Int = js("{ throw 42; }")
fun throwNullFromJs(): Int = js("{ throw null; }")

@JsName("TypeError")
external class JsTypeError : JsAny

inline fun <reified T : Throwable> wasThrown(fn: () -> Any?): Boolean {
    try {
        fn()
        return false
    } catch (e: Throwable) {
        return e is T
    }
    return true
}

// Finally only
fun jsPrimitiveWithFinally(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } finally {
        return true
    }
    return false
}
fun jsNumberWithFinally(): Boolean {
    try {
        throwNumberFromJs()
        return false
    } finally {
        return true
    }
    return false
}
fun jsNullWithFinally(): Boolean {
    try {
        throwNullFromJs()
        return false
    } finally {
        return true
    }
    return false
}
fun jsExceptionWithFinally(): Boolean {
    try {
        throwSomeJsException()
        return false
    } finally {
        return true
    }
    return false
}
fun kotlinExceptionWithFinally(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } finally {
        return true
    }
    return false
}

// Catch Throwable only
fun jsPrimitiveWithCatchThrowable(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: Throwable) {
        return e is JsException && e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    }
    return false
}
fun jsNumberWithCatchThrowable(): Boolean {
    try {
        throwNumberFromJs()
        return false
    } catch (e: Throwable) {
        return e is JsException &&
                e.message == "Exception was thrown while running JavaScript code" &&
                e.thrownValue == null
    }
    return false
}
fun jsNullWithCatchThrowable(): Boolean {
    try {
        throwNullFromJs()
        return false
    } catch (e: Throwable) {
        return e is JsException &&
                e.message == "Exception was thrown while running JavaScript code" &&
                e.thrownValue == null
    }
    return false
}
fun jsExceptionWithCatchThrowable(): Boolean {
    try {
        throwSomeJsException()
        return false
    } catch (e: Throwable) {
        return e is JsException && e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    }
    return false
}
fun kotlinExceptionWithCatchThrowable(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } catch (e: Throwable) {
        return e is IllegalStateException && e.message == "Test"
    }
    return false
}

// Catch JsException only
fun jsPrimitiveWithCatchJsException(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    }
    return false
}
fun jsNumberWithCatchJsException(): Boolean {
    try {
        throwNumberFromJs()
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    }
    return false
}
fun jsNullWithCatchJsException(): Boolean {
    try {
        throwNullFromJs()
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    }
    return false
}
fun jsExceptionWithCatchJsException(): Boolean {
    try {
        throwSomeJsException()
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    }
    return false
}
fun kotlinExceptionWithCatchJsException(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } catch (e: JsException) {
        return false
    }
    return true
}

// Catch IllegalStateException only
fun jsPrimitiveWithCatchIllegalStateException(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: IllegalStateException) {
        return false
    }
    return true
}
fun jsNumberWithCatchIllegalStateException(): Boolean {
    try {
        throwNumberFromJs()
        return false
    } catch (e: IllegalStateException) {
        return false
    }
    return true
}
fun jsNullWithCatchIllegalStateException(): Boolean {
    try {
        throwNullFromJs()
        return false
    } catch (e: IllegalStateException) {
        return false
    }
    return true
}
fun jsExceptionWithCatchIllegalStateException(): Boolean {
    try {
        throwSomeJsException()
        return false
    } catch (e: IllegalStateException) {
        return false
    }
    return true
}
fun kotlinExceptionWithCatchIllegalStateException(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } catch (e: IllegalStateException) {
        return e.message == "Test"
    }
    return false
}

// Catch Throwable and finally
fun jsPrimitiveWithCatchThrowableAndFinally(): Boolean {
    var finalException: Throwable? = null
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: Throwable) {
        finalException = e
    } finally {
        return finalException is JsException && finalException.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun jsNumberWithCatchThrowableAndFinally(): Boolean {
    var finalException: Throwable? = null
    try {
        throwNumberFromJs()
        return false
    } catch (e: Throwable) {
        finalException = e
    } finally {
        return finalException is JsException && finalException.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun jsNullWithCatchThrowableAndFinally(): Boolean {
    var finalException: Throwable? = null
    try {
        throwNullFromJs()
        return false
    } catch (e: Throwable) {
        finalException = e
    } finally {
        return finalException is JsException && finalException.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun jsExceptionWithCatchThrowableAndFinally(): Boolean {
    var finalException: Throwable? = null
    try {
        throwSomeJsException()
        return false
    } catch (e: Throwable) {
        finalException = e
    } finally {
        return finalException is JsException && finalException.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun kotlinExceptionWithCatchThrowableAndFinally(): Boolean {
    var finalException: Throwable? = null
    try {
        throwSomeKotlinException()
        return false
    } catch (e: Throwable) {
        finalException = e
    } finally {
        return finalException is IllegalStateException && finalException.message == "Test"
    }
    return false
}

// Catch JsException and finally
fun jsPrimitiveWithCatchJsExceptionAndFinally(): Boolean {
    var finalException: JsException? = null
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: JsException) {
        finalException = e
    } finally {
        return finalException?.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun jsNumberWithCatchJsExceptionAndFinally(): Boolean {
    var finalException: JsException? = null
    try {
        throwNumberFromJs()
        return false
    } catch (e: JsException) {
        finalException = e
    } finally {
        return finalException?.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun jsNullWithCatchJsExceptionAndFinally(): Boolean {
    var finalException: JsException? = null
    try {
        throwNullFromJs()
        return false
    } catch (e: JsException) {
        finalException = e
    } finally {
        return finalException?.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun jsExceptionWithCatchJsExceptionAndFinally(): Boolean {
    var finalException: JsException? = null
    try {
        throwSomeJsException()
        return false
    } catch (e: JsException) {
        finalException = e
    } finally {
        return finalException?.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun kotlinExceptionWithCatchJsExceptionAndFinally(): Boolean {
    var finalException: JsException? = null
    try {
        throwSomeKotlinException()
        return false
    } catch (e: JsException) {
        finalException = e
    } finally {
        return finalException == null
    }
    return true
}

// Catch IllegalStateException and finally
fun jsPrimitiveWithCatchIllegalStateExceptionAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
    } finally {
        return finalException == null
    }
    return true
}
fun jsNumberWithCatchIllegalStateExceptionAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    try {
        throwNumberFromJs()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
    } finally {
        return finalException == null
    }
    return true
}
fun jsNullWithCatchIllegalStateExceptionAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    try {
        throwNullFromJs()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
    } finally {
        return finalException == null
    }
    return true
}
fun jsExceptionWithCatchIllegalStateExceptionAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    try {
        throwSomeJsException()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
    } finally {
        return finalException == null
    }
    return true
}
fun kotlinExceptionWithCatchIllegalStateExceptionAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    try {
        throwSomeKotlinException()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
    } finally {
        return finalException?.message == "Test"
    }
    return false
}

// Catch JsException and Throwable
fun jsPrimitiveWithCatchJsExceptionAndThrowable(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: Throwable) {
        return false
    }
    return false
}
fun jsExceptionWithCatchJsExceptionAndThrowable(): Boolean {
    try {
        throwSomeJsException()
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: Throwable) {
        return false
    }
    return false
}
fun kotlinExceptionWithCatchJsExceptionAndThrowable(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } catch (e: JsException) {
        return false
    } catch (e: Throwable) {
        return true
    }
    return false
}

// Catch IllegalStateException and Throwable
fun jsPrimitiveWithCatchIllegalStateExceptionAndThrowable(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: IllegalStateException) {
        return false
    } catch (e: Throwable) {
        return e is JsException && e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    }
    return false
}
fun jsExceptionWithCatchIllegalStateExceptionAndThrowable(): Boolean {
    try {
        throwSomeJsException()
        return false
    } catch (e: IllegalStateException) {
        return false
    } catch (e: Throwable) {
        return e is JsException && e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    }
    return false
}
fun kotlinExceptionWithCatchIllegalStateExceptionAndThrowable(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } catch (e: IllegalStateException) {
        return e.message == "Test"
    } catch (e: Throwable) {
        return false
    }
    return false
}

// Catch Throwable and JsException
fun jsPrimitiveWithCatchThrowableAndJsException(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: Throwable) {
        return e is JsException && e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: JsException) {
        return false
    }
    return false
}
fun jsExceptionWithCatchThrowableAndJsException(): Boolean {
    try {
        throwSomeJsException()
        return false
    } catch (e: Throwable) {
        return e is JsException && e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: JsException) {
        return false
    }
    return false
}
fun kotlinExceptionWithCatchThrowableAndJsException(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } catch (e: Throwable) {
        return e is IllegalStateException && e.message == "Test"
    } catch (e: JsException) {
        return false
    }
    return false
}

// Catch IllegalStateException and JsException
fun jsPrimitiveWithCatchIllegalStateExceptionAndJsException(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: IllegalStateException) {
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    }
    return false
}
fun jsExceptionWithCatchIllegalStateExceptionAndJsException(): Boolean {
    try {
        throwSomeJsException()
        return false
    } catch (e: IllegalStateException) {
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    }
    return false
}
fun kotlinExceptionWithCatchIllegalStateExceptionAndJsException(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } catch (e: IllegalStateException) {
        return e.message == "Test"
    } catch (e: JsException) {
        return false
    }
    return false
}

// Catch Throwable and IllegalStateException
fun jsPrimitiveWithCatchThrowableAndIllegalStateException(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: Throwable) {
        return e is JsException && e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: IllegalStateException) {
        return false
    }
    return false
}
fun jsExceptionWithCatchThrowableAndIllegalStateException(): Boolean {
    try {
        throwSomeJsException()
        return false
    } catch (e: Throwable) {
        return e is JsException && e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: IllegalStateException) {
        return false
    }
    return false
}
fun kotlinExceptionWithCatchThrowableAndIllegalStateException(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } catch (e: Throwable) {
        return e is IllegalStateException && e.message == "Test"
    } catch (e: IllegalStateException) {
        return false
    }
    return false
}

// Catch JsException and IllegalStateException
fun jsPrimitiveWithCatchJsExceptionAndIllegalStateException(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: IllegalStateException) {
        return false
    }
    return false
}
fun jsExceptionWithCatchJsExceptionAndIllegalStateException(): Boolean {
    try {
        throwSomeJsException()
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: IllegalStateException) {
        return false
    }
    return false
}
fun kotlinExceptionWithCatchJsExceptionAndIllegalStateException(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } catch (e: JsException) {
        return true
    } catch (e: IllegalStateException) {
        return e.message == "Test"
    }
    return false
}

// Catch JsException and Throwable and finally
fun jsPrimitiveWithCatchJsExceptionAndThrowableAndFinally(): Boolean {
    var finalException: JsException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: JsException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException?.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun jsExceptionWithCatchJsExceptionAndThrowableAndFinally(): Boolean {
    var finalException: JsException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsException()
        return false
    } catch (e: JsException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException?.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun kotlinExceptionWithCatchJsExceptionAndThrowableAndFinally(): Boolean {
    var finalException: JsException? = null
    var somethingWasCaught = false
    try {
        throwSomeKotlinException()
        return false
    } catch (e: JsException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException == null
    }
    return false
}

// Catch IllegalStateException and Throwable and finally
fun jsPrimitiveWithCatchIllegalStateExceptionAndThrowableAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException == null
    }
    return false
}
fun jsExceptionWithCatchIllegalStateExceptionAndThrowableAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsException()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException == null
    }
    return false
}
fun kotlinExceptionWithCatchIllegalStateExceptionAndThrowableAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    var somethingWasCaught = false
    try {
        throwSomeKotlinException()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException?.message == "Test"
    }
    return false
}

// Catch Throwable and JsException and finally
fun jsPrimitiveWithCatchThrowableAndJsExceptionAndFinally(): Boolean {
    var finalException: Throwable? = null
    var somethingWasCaught = false
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: Throwable) {
        finalException = e
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException is JsException && finalException.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun jsExceptionWithCatchThrowableAndJsExceptionAndFinally(): Boolean {
    var finalException: Throwable? = null
    var somethingWasCaught = false
    try {
        throwSomeJsException()
        return false
    } catch (e: Throwable) {
        finalException = e
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException is JsException && finalException.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun kotlinExceptionWithCatchThrowableAndJsExceptionAndFinally(): Boolean {
    var finalException: Throwable? = null
    var somethingWasCaught = false
    try {
        throwSomeKotlinException()
        return false
    } catch (e: Throwable) {
        finalException = e
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException is IllegalStateException && finalException.message == "Test"
    }
    return false
}

// Catch IllegalStateException and JsException and finally
fun jsPrimitiveWithCatchIllegalStateExceptionAndJsExceptionAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException == null
    }
    return false
}
fun jsExceptionWithCatchIllegalStateExceptionAndJsExceptionAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsException()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException == null
    }
    return false
}
fun kotlinExceptionWithCatchIllegalStateExceptionAndJsExceptionAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    var somethingWasCaught = false
    try {
        throwSomeKotlinException()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException?.message == "Test"
    }
    return false
}

// Catch Throwable and IllegalStateException and finally
fun jsPrimitiveWithCatchThrowableAndIllegalStateExceptionAndFinally(): Boolean {
    var finalException: Throwable? = null
    var somethingWasCaught = false
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: Throwable) {
        finalException = e
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException is JsException && finalException.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun jsExceptionWithCatchThrowableAndIllegalStateExceptionAndFinally(): Boolean {
    var finalException: Throwable? = null
    var somethingWasCaught = false
    try {
        throwSomeJsException()
        return false
    } catch (e: Throwable) {
        finalException = e
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException is JsException && finalException.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun kotlinExceptionWithCatchThrowableAndIllegalStateExceptionAndFinally(): Boolean {
    var finalException: Throwable? = null
    var somethingWasCaught = false
    try {
        throwSomeKotlinException()
        return false
    } catch (e: Throwable) {
        finalException = e
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException is IllegalStateException && finalException.message == "Test"
    }
    return false
}

// Catch JsException and IllegalStateException and finally
fun jsPrimitiveWithCatchJsExceptionAndIllegalStateExceptionAndFinally(): Boolean {
    var finalException: JsException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: JsException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException?.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun jsExceptionWithCatchJsExceptionAndIllegalStateExceptionAndFinally(): Boolean {
    var finalException: JsException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsException()
        return false
    } catch (e: JsException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException?.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun kotlinExceptionWithCatchJsExceptionAndIllegalStateExceptionAndFinally(): Boolean {
    var finalException: JsException? = null
    var somethingWasCaught = false
    try {
        throwSomeKotlinException()
        return false
    } catch (e: JsException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException == null
    }
    return false
}

// Catch JsException and Throwable and IllegalStateException
fun jsPrimitiveWithCatchJsExceptionAndThrowableAndIllegalStateException(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: Throwable) {
        return false
    } catch (e: IllegalStateException) {
        return false
    }
    return false
}
fun jsExceptionWithCatchJsExceptionAndThrowableAndIllegalStateException(): Boolean {
    try {
        throwSomeJsException()
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: Throwable) {
        return false
    } catch (e: IllegalStateException) {
        return false
    }
    return false
}
fun kotlinExceptionWithCatchJsExceptionAndThrowableAndIllegalStateException(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } catch (e: JsException) {
        return false
    } catch (e: Throwable) {
        return e is IllegalStateException && e.message == "Test"
    } catch (e: IllegalStateException) {
        return false
    }
    return false
}

// Catch IllegalStateException and Throwable and JsException
fun jsPrimitiveWithCatchIllegalStateExceptionAndThrowableAndJsException(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: IllegalStateException) {
        return false
    } catch (e: Throwable) {
        return e is JsException && e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: JsException) {
        return false
    }
    return false
}
fun jsExceptionWithCatchIllegalStateExceptionAndThrowableAndJsException(): Boolean {
    try {
        throwSomeJsException()
        return false
    } catch (e: IllegalStateException) {
        return false
    } catch (e: Throwable) {
        return e is JsException && e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: JsException) {
        return false
    }
    return false
}
fun kotlinExceptionWithCatchIllegalStateExceptionAndThrowableAndJsException(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } catch (e: IllegalStateException) {
        return e.message == "Test"
    } catch (e: Throwable) {
        return false
    } catch (e: JsException) {
        return false
    }
    return false
}

// Catch Throwable and JsException and IllegalStateException
fun jsPrimitiveWithCatchThrowableAndJsExceptionAndIllegalStateException(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: Throwable) {
        return e is JsException && e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: JsException) {
        return false
    } catch (e: IllegalStateException) {
        return false
    }
    return false
}
fun jsExceptionWithCatchThrowableAndJsExceptionAndIllegalStateException(): Boolean {
    try {
        throwSomeJsException()
        return false
    } catch (e: Throwable) {
        return e is JsException && e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: JsException) {
        return false
    } catch (e: IllegalStateException) {
        return false
    }
    return false
}
fun kotlinExceptionWithCatchThrowableAndJsExceptionAndIllegalStateException(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } catch (e: Throwable) {
        return e is IllegalStateException && e.message == "Test"
    } catch (e: JsException) {
        return false
    } catch (e: IllegalStateException) {
        return false
    }
    return false
}

// Catch IllegalStateException and JsException and Throwable
fun jsPrimitiveWithCatchIllegalStateExceptionAndJsExceptionAndThrowable(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: IllegalStateException) {
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: Throwable) {
        return false
    }
    return false
}
fun jsExceptionWithCatchIllegalStateExceptionAndJsExceptionAndThrowable(): Boolean {
    try {
        throwSomeJsException()
        return false
    } catch (e: IllegalStateException) {
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: Throwable) {
        return false
    }
    return false
}
fun kotlinExceptionWithCatchIllegalStateExceptionAndJsExceptionAndThrowable(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } catch (e: IllegalStateException) {
        return e.message == "Test"
    } catch (e: JsException) {
        return false
    } catch (e: Throwable) {
        return false
    }
    return false
}

// Catch Throwable and IllegalStateException and JsException
fun jsPrimitiveWithCatchThrowableAndIllegalStateExceptionAndJsException(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: Throwable) {
        return e is JsException && e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: IllegalStateException) {
        return false
    } catch (e: JsException) {
        return false
    }
    return false
}
fun jsExceptionWithCatchThrowableAndIllegalStateExceptionAndJsException(): Boolean {
    try {
        throwSomeJsException()
        return false
    } catch (e: Throwable) {
        return e is JsException && e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: IllegalStateException) {
        return false
    } catch (e: JsException) {
        return false
    }
    return false
}
fun kotlinExceptionWithCatchThrowableAndIllegalStateExceptionAndJsException(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } catch (e: Throwable) {
        return e is IllegalStateException && e.message == "Test"
    } catch (e: IllegalStateException) {
        return false
    } catch (e: JsException) {
        return false
    }
    return false
}

// Catch JsException and IllegalStateException and Throwable
fun jsPrimitiveWithCatchJsExceptionAndIllegalStateExceptionAndThrowable(): Boolean {
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: IllegalStateException) {
        return false
    } catch (e: Throwable) {
        return false
    }
    return false
}
fun jsExceptionWithCatchJsExceptionAndIllegalStateExceptionAndThrowable(): Boolean {
    try {
        throwSomeJsException()
        return false
    } catch (e: JsException) {
        return e.message == "Exception was thrown while running JavaScript code" && e.thrownValue == null
    } catch (e: IllegalStateException) {
        return false
    } catch (e: Throwable) {
        return false
    }
    return false
}
fun kotlinExceptionWithCatchJsExceptionAndIllegalStateExceptionAndThrowable(): Boolean {
    try {
        throwSomeKotlinException()
        return false
    } catch (e: JsException) {
        return false
    } catch (e: IllegalStateException) {
        return e.message == "Test"
    } catch (e: Throwable) {
        return false
    }
    return false
}

// Catch JsException and Throwable and IllegalStateException and finally
fun jsPrimitiveWithCatchJsExceptionAndThrowableAndIllegalStateExceptionAndFinally(): Boolean {
    var finalException: JsException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: JsException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = null
        somethingWasCaught = false
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = false
    } finally {
        return somethingWasCaught && finalException?.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun jsExceptionWithCatchJsExceptionAndThrowableAndIllegalStateExceptionAndFinally(): Boolean {
    var finalException: JsException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsException()
        return false
    } catch (e: JsException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = null
        somethingWasCaught = false
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = false
    } finally {
        return somethingWasCaught && finalException?.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun kotlinExceptionWithCatchJsExceptionAndThrowableAndIllegalStateExceptionAndFinally(): Boolean {
    var finalException: JsException? = null
    var somethingWasCaught = false
    try {
        throwSomeKotlinException()
        return false
    } catch (e: JsException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = null
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException == null
    }
    return false
}

// Catch IllegalStateException and Throwable and JsException and finally
fun jsPrimitiveWithCatchIllegalStateExceptionAndThrowableAndJsExceptionAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = null
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException == null
    }
    return false
}
fun jsExceptionWithCatchIllegalStateExceptionAndThrowableAndJsExceptionAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsException()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = null
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException == null
    }
    return false
}
fun kotlinExceptionWithCatchIllegalStateExceptionAndThrowableAndJsExceptionAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    var somethingWasCaught = false
    try {
        throwSomeKotlinException()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = null
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException?.message == "Test"
    }
    return false
}

// Catch Throwable and JsException and IllegalStateException and finally
fun jsPrimitiveWithCatchThrowableAndJsExceptionAndIllegalStateExceptionAndFinally(): Boolean {
    var finalException: Throwable? = null
    var somethingWasCaught = false
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: Throwable) {
        finalException = e
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException is JsException && finalException.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun jsExceptionWithCatchThrowableAndJsExceptionAndIllegalStateExceptionAndFinally(): Boolean {
    var finalException: Throwable? = null
    var somethingWasCaught = false
    try {
        throwSomeJsException()
        return false
    } catch (e: Throwable) {
        finalException = e
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException is JsException && finalException.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun kotlinExceptionWithCatchThrowableAndJsExceptionAndIllegalStateExceptionAndFinally(): Boolean {
    var finalException: Throwable? = null
    var somethingWasCaught = false
    try {
        throwSomeKotlinException()
        return false
    } catch (e: Throwable) {
        finalException = e
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException is IllegalStateException && finalException.message == "Test"
    }
    return false
}

// Catch IllegalStateException and JsException and Throwable and finally
fun jsPrimitiveWithCatchIllegalStateExceptionAndJsExceptionAndThrowableAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = e as IllegalStateException
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException == null
    }
    return false
}
fun jsExceptionWithCatchIllegalStateExceptionAndJsExceptionAndThrowableAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsException()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = e as IllegalStateException
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException == null
    }
    return false
}
fun kotlinExceptionWithCatchIllegalStateExceptionAndJsExceptionAndThrowableAndFinally(): Boolean {
    var finalException: IllegalStateException? = null
    var somethingWasCaught = false
    try {
        throwSomeKotlinException()
        return false
    } catch (e: IllegalStateException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException?.message == "Test"
    }
    return false
}

// Catch Throwable and IllegalStateException and JsException and finally
fun jsPrimitiveWithCatchThrowableAndIllegalStateExceptionAndJsExceptionAndFinally(): Boolean {
    var finalException: Throwable? = null
    var somethingWasCaught = false
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: Throwable) {
        finalException = e
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException is JsException && finalException.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun jsExceptionWithCatchThrowableAndIllegalStateExceptionAndJsExceptionAndFinally(): Boolean {
    var finalException: Throwable? = null
    var somethingWasCaught = false
    try {
        throwSomeJsException()
        return false
    } catch (e: Throwable) {
        finalException = e
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException is JsException && finalException.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun kotlinExceptionWithCatchThrowableAndIllegalStateExceptionAndJsExceptionAndFinally(): Boolean {
    var finalException: Throwable? = null
    var somethingWasCaught = false
    try {
        throwSomeKotlinException()
        return false
    } catch (e: Throwable) {
        finalException = e
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } catch (e: JsException) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException is IllegalStateException && finalException.message == "Test"
    }
    return false
}

// Catch JsException and IllegalStateException and Throwable and finally
fun jsPrimitiveWithCatchJsExceptionAndIllegalStateExceptionAndThrowableAndFinally(): Boolean {
    var finalException: JsException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsPrimitive()
        return false
    } catch (e: JsException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException?.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun jsExceptionWithCatchJsExceptionAndIllegalStateExceptionAndThrowableAndFinally(): Boolean {
    var finalException: JsException? = null
    var somethingWasCaught = false
    try {
        throwSomeJsException()
        return false
    } catch (e: JsException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = null
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException?.message == "Exception was thrown while running JavaScript code" && finalException.thrownValue == null
    }
    return false
}
fun kotlinExceptionWithCatchJsExceptionAndIllegalStateExceptionAndThrowableAndFinally(): Boolean {
    var finalException: JsException? = null
    var somethingWasCaught = false
    try {
        throwSomeKotlinException()
        return false
    } catch (e: JsException) {
        finalException = e
        somethingWasCaught = true
    } catch (e: IllegalStateException) {
        finalException = null
        somethingWasCaught = true
    } catch (e: Throwable) {
        finalException = e as JsException
        somethingWasCaught = true
    } finally {
        return somethingWasCaught && finalException == null
    }
    return false
}

fun box(): String {
    // Finally only
    if (!jsPrimitiveWithFinally()) return "Issue with try with finally on a JS primitive thrown"
    if (!jsNumberWithFinally()) return "Issue with try with finally on a JS number thrown"
    if (!jsNullWithFinally()) return "Issue with try with finally on a JS null thrown"
    if (!jsExceptionWithFinally()) return "Issue with try with finally on a JS Error thrown"
    if (!kotlinExceptionWithFinally()) return "Issue with try with finally on a Kotlin Exception thrown"

    // Catch Throwable only
    if (!jsPrimitiveWithCatchThrowable()) return "Issue with try with catch Throwable only on a JS primitive thrown"
    if (!jsNumberWithCatchThrowable()) return "Issue with try with catch Throwable only on a JS number thrown"
    if (!jsNullWithCatchThrowable()) return "Issue with try with catch Throwable only on a JS null thrown"
    if (!jsExceptionWithCatchThrowable()) return "Issue with try with catch Throwable only on a JS Error thrown"
    if (!kotlinExceptionWithCatchThrowable()) return "Issue with try with catch Throwable only on a Kotlin Exception thrown"

    // Catch JsException only
    if (!jsPrimitiveWithCatchJsException()) return "Issue with try with catch JsException only on a JS primitive thrown"
    if (!jsNumberWithCatchJsException()) return "Issue with try with catch JsException only on a JS number thrown"
    if (!jsNullWithCatchJsException()) return "Issue with try with catch JsException only on a JS null thrown"
    if (!jsExceptionWithCatchJsException()) return "Issue with try with catch JsException only on a JS Error thrown"
    if (!wasThrown<IllegalStateException> {  kotlinExceptionWithCatchJsException() }) return "Issue with try with catch JsException only on a Kotlin Exception thrown"

    // Catch IllegalStateException only
    if (!wasThrown<JsException> { jsPrimitiveWithCatchIllegalStateException() }) return "Issue with try with catch IllegalStateException only on a JS primitive thrown"
    if (!wasThrown<JsException> { jsNumberWithCatchIllegalStateException() }) return "Issue with try with catch IllegalStateException only on a JS number thrown"
    if (!wasThrown<JsException> { jsNullWithCatchIllegalStateException() }) return "Issue with try with catch IllegalStateException only on a JS null thrown"
    if (!wasThrown<JsException> { jsExceptionWithCatchIllegalStateException() }) return "Issue with try with catch IllegalStateException only on a JS Error thrown"
    if (!kotlinExceptionWithCatchIllegalStateException()) return "Issue with try with catch IllegalStateException only on a Kotlin Exception thrown"

    // Catch Throwable and finally
    if (!jsPrimitiveWithCatchThrowableAndFinally()) return "Issue with try with catch Throwable and finally on a JS primitive thrown"
    if (!jsNumberWithCatchThrowableAndFinally()) return "Issue with try with catch Throwable and finally on a JS number thrown"
    if (!jsNullWithCatchThrowableAndFinally()) return "Issue with try with catch Throwable and finally on a JS null thrown"
    if (!jsExceptionWithCatchThrowableAndFinally()) return "Issue with try with catch Throwable and finally on a JS Error thrown"
    if (!kotlinExceptionWithCatchThrowableAndFinally()) return "Issue with try with catch Throwable and finally on a Kotlin Exception thrown"

    // Catch JsException and finally
    if (!jsPrimitiveWithCatchJsExceptionAndFinally()) return "Issue with try with catch JsException and finally on a JS primitive thrown"
    if (!jsNumberWithCatchJsExceptionAndFinally()) return "Issue with try with catch JsException and finally on a JS number thrown"
    if (!jsNullWithCatchJsExceptionAndFinally()) return "Issue with try with catch JsException and finally on a JS null thrown"
    if (!jsExceptionWithCatchJsExceptionAndFinally()) return "Issue with try with catch JsException and finally on a JS Error thrown"
    if (!kotlinExceptionWithCatchJsExceptionAndFinally()) return "Issue with try with catch JsException only on a Kotlin Exception thrown"

    // Catch IllegalStateException and finally
    if (!jsPrimitiveWithCatchIllegalStateExceptionAndFinally()) return "Issue with try with catch IllegalStateException and finally on a JS primitive thrown"
    if (!jsNumberWithCatchIllegalStateExceptionAndFinally()) return "Issue with try with catch IllegalStateException and finally on a JS number thrown"
    if (!jsNullWithCatchIllegalStateExceptionAndFinally()) return "Issue with try with catch IllegalStateException and finally on a JS null thrown"
    if (!jsExceptionWithCatchIllegalStateExceptionAndFinally()) return "Issue with try with catch IllegalStateException and finally on a JS Error thrown"
    if (!kotlinExceptionWithCatchIllegalStateExceptionAndFinally()) return "Issue with try with catch IllegalStateException and finally on a Kotlin Exception thrown"

    // Catch JsException and Throwable
    if (!jsPrimitiveWithCatchJsExceptionAndThrowable()) return "Issue with try with catch JsException and Throwable on a JS primitive thrown"
    if (!jsExceptionWithCatchJsExceptionAndThrowable()) return "Issue with try with catch JsException and Throwable on a JS Error thrown"
    if (!kotlinExceptionWithCatchJsExceptionAndThrowable()) return "Issue with try with catch JsException and Throwable on a Kotlin Exception thrown"

    // Catch IllegalStateException and Throwable
    if (!jsPrimitiveWithCatchIllegalStateExceptionAndThrowable()) return "Issue with try with catch IllegalStateException and Throwable on a JS primitive thrown"
    if (!jsExceptionWithCatchIllegalStateExceptionAndThrowable()) return "Issue with try with catch IllegalStateException and Throwable on a JS Error thrown"
    if (!kotlinExceptionWithCatchIllegalStateExceptionAndThrowable()) return "Issue with try with catch IllegalStateException and Throwable on a Kotlin Exception thrown"

    // Catch Throwable and JsException
    if (!jsPrimitiveWithCatchThrowableAndJsException()) return "Issue with try with catch Throwable and JsException on a JS primitive thrown"
    if (!jsExceptionWithCatchThrowableAndJsException()) return "Issue with try with catch Throwable and JsException on a JS Error thrown"
    if (!kotlinExceptionWithCatchThrowableAndJsException()) return "Issue with try with catch Throwable and JsException on a Kotlin Exception thrown"

    // Catch IllegalStateException and JsException
    if (!jsPrimitiveWithCatchIllegalStateExceptionAndJsException()) return "Issue with try with catch IllegalStateException and JsException on a JS primitive thrown"
    if (!jsExceptionWithCatchIllegalStateExceptionAndJsException()) return "Issue with try with catch IllegalStateException and JsException on a JS Error thrown"
    if (!kotlinExceptionWithCatchIllegalStateExceptionAndJsException()) return "Issue with try with catch IllegalStateException and JsException on a Kotlin Exception thrown"

    // Catch Throwable and IllegalStateException
    if (!jsPrimitiveWithCatchThrowableAndIllegalStateException()) return "Issue with try with catch Throwable and IllegalStateException on a JS primitive thrown"
    if (!jsExceptionWithCatchThrowableAndIllegalStateException()) return "Issue with try with catch Throwable and IllegalStateException on a JS Error thrown"
    if (!kotlinExceptionWithCatchThrowableAndIllegalStateException()) return "Issue with try with catch Throwable and IllegalStateException on a Kotlin Exception thrown"

    // Catch JsException and IllegalStateException
    if (!jsPrimitiveWithCatchJsExceptionAndIllegalStateException()) return "Issue with try with catch JsException and IllegalStateException on a JS primitive thrown"
    if (!jsExceptionWithCatchJsExceptionAndIllegalStateException()) return "Issue with try with catch JsException and IllegalStateException on a JS Error thrown"
    if (!kotlinExceptionWithCatchJsExceptionAndIllegalStateException()) return "Issue with try with catch JsException and IllegalStateException on a Kotlin Exception thrown"

    // Catch JsException and Throwable and finally
    if (!jsPrimitiveWithCatchJsExceptionAndThrowableAndFinally()) return "Issue with try with catch JsException and Throwable and finally on a JS primitive thrown"
    if (!jsExceptionWithCatchJsExceptionAndThrowableAndFinally()) return "Issue with try with catch JsException and Throwable and finally on a JS Error thrown"
    if (!kotlinExceptionWithCatchJsExceptionAndThrowableAndFinally()) return "Issue with try with catch JsException and Throwable and finally on a Kotlin Exception thrown"

    // Catch IllegalStateException and Throwable and finally
    if (!jsPrimitiveWithCatchIllegalStateExceptionAndThrowableAndFinally()) return "Issue with try with catch IllegalStateException and Throwable and finally on a JS primitive thrown"
    if (!jsExceptionWithCatchIllegalStateExceptionAndThrowableAndFinally()) return "Issue with try with catch IllegalStateException and Throwable and finally on a JS Error thrown"
    if (!kotlinExceptionWithCatchIllegalStateExceptionAndThrowableAndFinally()) return "Issue with try with catch IllegalStateException and Throwable and finally on a Kotlin Exception thrown"

    // Catch Throwable and JsException and finally
    if (!jsPrimitiveWithCatchThrowableAndJsExceptionAndFinally()) return "Issue with try with catch Throwable and JsException and finally on a JS primitive thrown"
    if (!jsExceptionWithCatchThrowableAndJsExceptionAndFinally()) return "Issue with try with catch Throwable and JsException and finally on a JS Error thrown"
    if (!kotlinExceptionWithCatchThrowableAndJsExceptionAndFinally()) return "Issue with try with catch Throwable and JsException and finally on a Kotlin Exception thrown"

    // Catch IllegalStateException and JsException and finally
    if (!jsPrimitiveWithCatchIllegalStateExceptionAndJsExceptionAndFinally()) return "Issue with try with catch IllegalStateException and JsException and finally on a JS primitive thrown"
    if (!jsExceptionWithCatchIllegalStateExceptionAndJsExceptionAndFinally()) return "Issue with try with catch IllegalStateException and JsException and finally on a JS Error thrown"
    if (!kotlinExceptionWithCatchIllegalStateExceptionAndJsExceptionAndFinally()) return "Issue with try with catch IllegalStateException and JsException and finally on a Kotlin Exception thrown"

    // Catch Throwable and IllegalStateException and finally
    if (!jsPrimitiveWithCatchThrowableAndIllegalStateExceptionAndFinally()) return "Issue with try with catch Throwable and IllegalStateException and finally on a JS primitive thrown"
    if (!jsExceptionWithCatchThrowableAndIllegalStateExceptionAndFinally()) return "Issue with try with catch Throwable and IllegalStateException and finally on a JS Error thrown"
    if (!kotlinExceptionWithCatchThrowableAndIllegalStateExceptionAndFinally()) return "Issue with try with catch Throwable and IllegalStateException and finally on a Kotlin Exception thrown"

    // Catch JsException and IllegalStateException and finally
    if (!jsPrimitiveWithCatchJsExceptionAndIllegalStateExceptionAndFinally()) return "Issue with try with catch JsException and IllegalStateException and finally on a JS primitive thrown"
    if (!jsExceptionWithCatchJsExceptionAndIllegalStateExceptionAndFinally()) return "Issue with try with catch JsException and IllegalStateException and finally on a JS Error thrown"
    if (!kotlinExceptionWithCatchJsExceptionAndIllegalStateExceptionAndFinally()) return "Issue with try with catch JsException and IllegalStateException and finally on a Kotlin Exception thrown"

    // Catch JsException and Throwable and IllegalStateException
    if (!jsPrimitiveWithCatchJsExceptionAndThrowableAndIllegalStateException()) return "Issue with try with catch JsException and Throwable and IllegalStateException on a JS primitive thrown"
    if (!jsExceptionWithCatchJsExceptionAndThrowableAndIllegalStateException()) return "Issue with try with catch JsException and Throwable and IllegalStateException on a JS Error thrown"
    if (!kotlinExceptionWithCatchJsExceptionAndThrowableAndIllegalStateException()) return "Issue with try with catch JsException and Throwable and IllegalStateException on a Kotlin Exception thrown"

    // Catch IllegalStateException and Throwable and JsException
    if (!jsPrimitiveWithCatchIllegalStateExceptionAndThrowableAndJsException()) return "Issue with try with catch IllegalStateException and Throwable and JsException on a JS primitive thrown"
    if (!jsExceptionWithCatchIllegalStateExceptionAndThrowableAndJsException()) return "Issue with try with catch IllegalStateException and Throwable and JsException on a JS Error thrown"
    if (!kotlinExceptionWithCatchIllegalStateExceptionAndThrowableAndJsException()) return "Issue with try with catch IllegalStateException and Throwable and JsException on a Kotlin Exception thrown"

    // Catch Throwable and JsException and IllegalStateException
    if (!jsPrimitiveWithCatchThrowableAndJsExceptionAndIllegalStateException()) return "Issue with try with catch Throwable and JsException and IllegalStateException on a JS primitive thrown"
    if (!jsExceptionWithCatchThrowableAndJsExceptionAndIllegalStateException()) return "Issue with try with catch Throwable and JsException and IllegalStateException on a JS Error thrown"
    if (!kotlinExceptionWithCatchThrowableAndJsExceptionAndIllegalStateException()) return "Issue with try with catch Throwable and JsException and IllegalStateException on a Kotlin Exception thrown"

    // Catch IllegalStateException and JsException and Throwable
    if (!jsPrimitiveWithCatchIllegalStateExceptionAndJsExceptionAndThrowable()) return "Issue with try with catch IllegalStateException and JsException and Throwable on a JS primitive thrown"
    if (!jsExceptionWithCatchIllegalStateExceptionAndJsExceptionAndThrowable()) return "Issue with try with catch IllegalStateException and JsException and Throwable on a JS Error thrown"
    if (! kotlinExceptionWithCatchIllegalStateExceptionAndJsExceptionAndThrowable()) return "Issue with try with catch IllegalStateException and JsException and Throwable on a Kotlin Exception thrown"

    // Catch Throwable and IllegalStateException and JsException
    if (!jsPrimitiveWithCatchThrowableAndIllegalStateExceptionAndJsException()) return "Issue with try with catch Throwable and IllegalStateException and JsException on a JS primitive thrown"
    if (!jsExceptionWithCatchThrowableAndIllegalStateExceptionAndJsException()) return "Issue with try with catch Throwable and IllegalStateException and JsException on a JS Error thrown"
    if (!kotlinExceptionWithCatchThrowableAndIllegalStateExceptionAndJsException()) return "Issue with try with catch Throwable and IllegalStateException and JsException on a Kotlin Exception thrown"

    // Catch JsException and IllegalStateException and Throwable
    if (!jsPrimitiveWithCatchJsExceptionAndIllegalStateExceptionAndThrowable()) return "Issue with try with catch JsException and IllegalStateException and Throwable on a JS primitive thrown"
    if (!jsExceptionWithCatchJsExceptionAndIllegalStateExceptionAndThrowable()) return "Issue with try with catch JsException and IllegalStateException and Throwable on a JS Error thrown"
    if (!kotlinExceptionWithCatchJsExceptionAndIllegalStateExceptionAndThrowable()) return "Issue with try with catch JsException and IllegalStateException and Throwable on a Kotlin Exception thrown"

    // Catch JsException and Throwable and IllegalStateException and finally
    if (!jsPrimitiveWithCatchJsExceptionAndThrowableAndIllegalStateExceptionAndFinally()) return "Issue with try with catch JsException and Throwable and IllegalStateException and finally on a JS primitive thrown"
    if (!jsExceptionWithCatchJsExceptionAndThrowableAndIllegalStateExceptionAndFinally()) return "Issue with try with catch JsException and Throwable and IllegalStateException and finally on a JS Error thrown"
    if (!kotlinExceptionWithCatchJsExceptionAndThrowableAndIllegalStateExceptionAndFinally()) return "Issue with try with catch JsException and Throwable and IllegalStateException and finally on a Kotlin Exception thrown"

    // Catch IllegalStateException and Throwable and JsException and finally
    if (!jsPrimitiveWithCatchIllegalStateExceptionAndThrowableAndJsExceptionAndFinally()) return "Issue with try with catch IllegalStateException and Throwable and JsException and finally on a JS primitive thrown"
    if (!jsExceptionWithCatchIllegalStateExceptionAndThrowableAndJsExceptionAndFinally()) return "Issue with try with catch IllegalStateException and Throwable and JsException and finally on a JS Error thrown"
    if (!kotlinExceptionWithCatchIllegalStateExceptionAndThrowableAndJsExceptionAndFinally()) return "Issue with try with catch IllegalStateException and Throwable and JsException and finally on a JS Error thrown"

    // Catch Throwable and JsException and IllegalStateException and finally
    if (!jsPrimitiveWithCatchThrowableAndJsExceptionAndIllegalStateExceptionAndFinally()) return "Issue with try with catch Throwable and JsException and IllegalStateException and finally on a JS primitive thrown"
    if (!jsExceptionWithCatchThrowableAndJsExceptionAndIllegalStateExceptionAndFinally()) return "Issue with try with catch Throwable and JsException and IllegalStateException and finally on a JS Error thrown"
    if (!kotlinExceptionWithCatchThrowableAndJsExceptionAndIllegalStateExceptionAndFinally()) return "Issue with try with catch Throwable and JsException and IllegalStateException and finally on a Kotlin Exception thrown"

    // Catch IllegalStateException and JsException and Throwable and finally
    if (!jsPrimitiveWithCatchIllegalStateExceptionAndJsExceptionAndThrowableAndFinally()) return "Issue with try with catch IllegalStateException and JsException and Throwable and finally on a JS primitive thrown"
    if (!jsExceptionWithCatchIllegalStateExceptionAndJsExceptionAndThrowableAndFinally()) return "Issue with try with catch IllegalStateException and JsException and Throwable and finally on a JS Error thrown"
    if (!kotlinExceptionWithCatchIllegalStateExceptionAndJsExceptionAndThrowableAndFinally()) return "Issue with try with catch IllegalStateException and JsException and Throwable and finally on a JS Error thrown"

    // Catch Throwable and IllegalStateException and JsException and finally
    if (!jsPrimitiveWithCatchThrowableAndIllegalStateExceptionAndJsExceptionAndFinally()) return "Issue with try with catch Throwable and IllegalStateException and JsException and finally on a JS primitive thrown"
    if (!jsExceptionWithCatchThrowableAndIllegalStateExceptionAndJsExceptionAndFinally()) return "Issue with try with catch Throwable and IllegalStateException and JsException and finally on a JS Error thrown"
    if (!kotlinExceptionWithCatchThrowableAndIllegalStateExceptionAndJsExceptionAndFinally()) return "Issue with try with catch Throwable and IllegalStateException and JsException and finally on a Kotlin Exception thrown"

    // Catch JsException and IllegalStateException and Throwable and finally
    if (!jsPrimitiveWithCatchJsExceptionAndIllegalStateExceptionAndThrowableAndFinally()) return "Issue with try with catch JsException and IllegalStateException and Throwable and finally on a JS primitive thrown"
    if (!jsExceptionWithCatchJsExceptionAndIllegalStateExceptionAndThrowableAndFinally()) return "Issue with try with catch JsException and IllegalStateException and Throwable and finally on a JS Error thrown"
    if (!kotlinExceptionWithCatchJsExceptionAndIllegalStateExceptionAndThrowableAndFinally()) return "Issue with try with catch JsException and IllegalStateException and Throwable and finally on a Kotlin Exception thrown"

    return "OK"
}
