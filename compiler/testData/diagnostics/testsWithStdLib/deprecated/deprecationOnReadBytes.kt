// RUN_PIPELINE_TILL: FRONTEND
// FIR_IDENTICAL
import java.io.InputStream

fun InputStream.test() {
    readBytes()

    <!DEPRECATION_ERROR!>readBytes<!>(1)
}

/* GENERATED_FIR_TAGS: funWithExtensionReceiver, functionDeclaration, integerLiteral */
