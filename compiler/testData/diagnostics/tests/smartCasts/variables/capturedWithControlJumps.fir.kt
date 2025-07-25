// RUN_PIPELINE_TILL: FRONTEND
// ISSUE: KT-50092
// SKIP_TXT

fun test1() {
    var x: String? = "..."
    var lambda: (() -> Int)? = null
    for (i in 1..2) {
        when (i) {
            2 -> x = null
            1 -> if (x != null) lambda = { <!SMARTCAST_IMPOSSIBLE!>x<!>.length } // bad
        }
    }
    lambda?.invoke()
}

fun test2() {
    var x: String? = "..."
    if (x != null) {
        val lambda = { <!SMARTCAST_IMPOSSIBLE!>x<!>.length } // bad
        while (false) return
        x = null
        lambda()
    }
}

fun test3() {
    var x: String? = "asd"
    if (x != null) {
        var lambda: (() -> Int)? = null
        try {
            lambda = { <!SMARTCAST_IMPOSSIBLE!>x<!>.length } // bad
            if (true) throw RuntimeException("...")
            return
        } catch (e: Exception) {
            x = null
            lambda?.invoke()
        } finally {
            x = null
            lambda?.invoke()
        }
    }
}

fun test4() {
    var x: String? = "..."
    if (x != null) {
        var lambda: (() -> Int)? = null
        while (true) {
            lambda = { <!SMARTCAST_IMPOSSIBLE!>x<!>.length } // bad
            if (true) break
            return
        }
        x = null
        lambda<!UNNECESSARY_SAFE_CALL!>?.<!>invoke()
    }
}

fun test5() {
    var lambda: (() -> Int)? = null
    for (i in 1..2) {
        lambda = {
            var x: String?
            x = ""
            x.length // ok
        }
    }
    lambda?.invoke()
}

fun test6() {
    var lambda: () -> Unit = { }
    for (x in 1..10) {
        var s: String? = null
        for (y in 1..10) {
            s = null
            lambda()
            s = ""
            lambda = { <!SMARTCAST_IMPOSSIBLE!>s<!>.length } // bad - next iteration will assign s = null
        }

        if (s != null) {
            lambda = { s.length } // ok - s about to go out of scope
        }
    }
}

/* GENERATED_FIR_TAGS: assignment, break, equalityExpression, forLoop, functionDeclaration, functionalType, ifExpression,
integerLiteral, lambdaLiteral, localProperty, nullableType, propertyDeclaration, rangeExpression, safeCall, smartcast,
stringLiteral, tryExpression, whenExpression, whenWithSubject, whileLoop */
