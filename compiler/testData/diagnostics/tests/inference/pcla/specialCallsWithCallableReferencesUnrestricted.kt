// RUN_PIPELINE_TILL: FRONTEND
// LANGUAGE: +UnrestrictedBuilderInference
// WITH_STDLIB
// SKIP_TXT
// DIAGNOSTICS: -CAST_NEVER_SUCCEEDS -UNCHECKED_CAST -UNUSED_PARAMETER -UNUSED_VARIABLE -OPT_IN_USAGE_ERROR -UNUSED_EXPRESSION
// KT-47797

import kotlin.experimental.ExperimentalTypeInference

fun <K> FlowCollector<K>.bar(): K = null as K
fun <K> FlowCollector<K>.foo(): K = null as K

fun <K> K.bar3(): K = null as K
fun <K> K.foo3(): K = null as K

fun bar2(): Int = 1
fun foo2(): Float = 1f

val bar4: Int
    get() = 1

var foo4: Float
    get() = 1f
    set(value) {}

val <K> FlowCollector<K>.bar5: K get() = null as K
val <K> FlowCollector<K>.foo5: K get() = null as K

class Foo6

class Foo7<T>
fun foo7() = null as Foo7<Int>

interface FlowCollector<in T> {}

fun <L> flow(block: suspend FlowCollector<L>.() -> Unit): Flow<L> = Flow(block)

class Flow<out R>(private val block: suspend FlowCollector<R>.() -> Unit)

fun <R> select(vararg x: R) = x[0]

fun poll0(): Flow<String> {
    return flow {
        val inv = select(::bar, ::foo)
        inv()
    }
}

fun poll01(): Flow<String> {
    return flow {
        val inv = select(::bar2, ::foo2)
        inv()
    }
}

fun poll02(): Flow<String> {
    return flow {
        val inv = select(::bar3, ::foo3)
        inv()
    }
}

fun poll03(): Flow<String> {
    return flow {
        val inv = select(::bar4, ::foo4)
        inv()
    }
}

fun poll04(): Flow<String> {
    return flow {
        val inv = select(::bar5, ::foo5)
        inv
    }
}

fun poll05(): Flow<String> {
    return flow {
        val inv = select(::Foo6, ::Foo6)
        inv
    }
}

fun poll06(): Flow<String> {
    return flow {
        val inv = select(foo7(), <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::Foo7<!>)
        inv
    }
}

fun poll1(flag: Boolean): Flow<String> {
    return flow {
        val inv = if (flag) { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar2<!><!> } else { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo2<!><!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll11(flag: Boolean): Flow<String> {
    return flow {
        val inv = if (flag) { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar2<!><!> } else { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo2<!><!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll12(flag: Boolean): Flow<String> {
    return flow {
        val inv = if (flag) { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar3<!><!> } else { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo3<!><!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll13(flag: Boolean): Flow<String> {
    return flow {
        val inv = if (flag) { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar2<!><!> } else { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo3<!><!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll14(flag: Boolean): Flow<String> {
    return flow {
        val inv = if (flag) { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar4<!><!> } else { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo4<!><!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll15(flag: Boolean): Flow<String> {
    return flow {
        val inv = if (flag) { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar5<!><!> } else { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo5<!><!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll16(flag: Boolean): Flow<String> {
    return flow {
        val inv = if (flag) { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>Foo6<!><!> } else { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>Foo6<!><!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll17(flag: Boolean): Flow<String> {
    return flow {
        val inv = if (flag) { foo7() } else { ::<!DEBUG_INFO_MISSING_UNRESOLVED!>Foo7<!> }
        inv
    }
}

fun poll2(flag: Boolean): Flow<String> {
    return flow {
        val inv = when (flag) { true -> ::bar else -> ::foo }
        inv()
    }
}

fun poll21(flag: Boolean): Flow<String> {
    return flow {
        val inv = when (flag) { true -> ::bar2 else -> ::foo2 }
        inv()
    }
}

fun poll22(flag: Boolean): Flow<String> {
    return flow {
        val inv = when (flag) { true -> ::bar3 else -> ::foo3 }
        inv()
    }
}

fun poll23(flag: Boolean): Flow<String> {
    return flow {
        val inv = when (flag) { true -> ::bar4 else -> ::foo4 }
        inv()
    }
}

fun poll24(flag: Boolean): Flow<String> {
    return flow {
        val inv = when (flag) { true -> ::bar5 else -> ::foo5 }
        inv
    }
}

fun poll25(flag: Boolean): Flow<String> {
    return flow {
        val inv = when (flag) { true -> ::Foo6 else -> ::Foo6 }
        inv
    }
}

fun poll26(flag: Boolean): Flow<String> {
    return flow {
        val inv = when (flag) { true -> <!IMPLICIT_CAST_TO_ANY, NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::Foo7<!> false -> <!IMPLICIT_CAST_TO_ANY!>foo7()<!> <!REDUNDANT_ELSE_IN_WHEN!>else<!> -> <!IMPLICIT_CAST_TO_ANY, NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::Foo7<!> }
        inv
    }
}

fun poll3(flag: Boolean): Flow<String> {
    return flow {
        val inv = when (flag) { true -> ::bar false -> ::foo }
        inv()
    }
}

fun poll31(flag: Boolean): Flow<String> {
    return flow {
        val inv = when (flag) { true -> ::bar2 false -> ::foo2 }
        inv()
    }
}

fun poll32(flag: Boolean): Flow<String> {
    return flow {
        val inv = when (flag) { true -> ::bar3 false -> ::foo3 }
        inv()
    }
}

fun poll33(flag: Boolean): Flow<String> {
    return flow {
        val inv = when (flag) { true -> ::bar4 false -> ::foo4 }
        inv()
    }
}

fun poll34(flag: Boolean): Flow<String> {
    return flow {
        val inv = when (flag) { true -> ::bar5 false -> ::foo5 }
        inv
    }
}

fun poll35(flag: Boolean): Flow<String> {
    return flow {
        val inv = when (flag) { true -> ::Foo6 false -> ::Foo6 }
        inv
    }
}

fun poll36(flag: Boolean): Flow<String> {
    return flow {
        val inv = when (flag) { true -> <!IMPLICIT_CAST_TO_ANY, NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::Foo7<!> false -> <!IMPLICIT_CAST_TO_ANY!>foo7()<!> }
        inv
    }
}

fun poll4(): Flow<String> {
    return flow {
        val inv = try { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar<!><!> } finally { ::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo<!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll41(): Flow<String> {
    return flow {
        val inv = try { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar2<!><!> } finally { ::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo2<!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll42(): Flow<String> {
    return flow {
        val inv = try { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar3<!><!> } finally { ::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo3<!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll43(): Flow<String> {
    return flow {
        val inv = try { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar4<!><!> } finally { ::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo4<!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll44(): Flow<String> {
    return flow {
        val inv = try { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar5<!><!> } finally { ::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo5<!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll45(): Flow<String> {
    return flow {
        val inv = try { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>Foo6<!><!> } finally { ::<!DEBUG_INFO_MISSING_UNRESOLVED!>Foo6<!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll46(): Flow<String> {
    return flow {
        val inv = try { foo7() } finally { ::<!DEBUG_INFO_MISSING_UNRESOLVED!>Foo7<!> }
        inv
    }
}

fun poll5(): Flow<String> {
    return flow {
        val inv = try { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar<!><!> } catch (e: Exception) { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo<!><!> } finally { ::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo<!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll51(): Flow<String> {
    return flow {
        val inv = try { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar2<!><!> } catch (e: Exception) { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo2<!><!> } finally { ::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo2<!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll52(): Flow<String> {
    return flow {
        val inv = try { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar3<!><!> } catch (e: Exception) { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo3<!><!> } finally { ::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo3<!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll53(): Flow<String> {
    return flow {
        val inv = try { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar4<!><!> } catch (e: Exception) { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo4<!><!> } finally { ::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo4<!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll54(): Flow<String> {
    return flow {
        val inv = try { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>bar5<!><!> } catch (e: Exception) { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo5<!><!> } finally { ::<!DEBUG_INFO_MISSING_UNRESOLVED!>foo5<!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll55(): Flow<String> {
    return flow {
        val inv = try { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>Foo6<!><!> } catch (e: Exception) { <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::<!DEBUG_INFO_MISSING_UNRESOLVED!>Foo6<!><!> } finally { ::<!DEBUG_INFO_MISSING_UNRESOLVED!>Foo6<!> }
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll56(): Flow<String> {
    return flow {
        val inv = try { ::<!DEBUG_INFO_MISSING_UNRESOLVED!>Foo7<!> } catch (e: Exception) { foo7() } finally { foo7() }
        inv
    }
}

fun poll6(): Flow<String> {
    return flow {
        val inv = ::bar
        inv
    }
}

fun poll61(): Flow<String> {
    return flow {
        val inv = ::bar2
        inv
    }
}

fun poll62(): Flow<String> {
    return flow {
        val inv = ::bar3
        inv
    }
}

fun poll63(): Flow<String> {
    return flow {
        val inv = ::bar4
        inv
    }
}

fun poll64(): Flow<String> {
    return flow {
        val inv = ::bar5
        inv
    }
}

fun poll65(): Flow<String> {
    return flow {
        val inv = ::Foo6
        inv
    }
}

fun poll66(): Flow<String> {
    return flow {
        val inv = ::<!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>Foo7<!>
        <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>inv<!>
    }
}

fun poll7(): Flow<String> {
    return flow {
        val inv = ::bar<!NOT_NULL_ASSERTION_ON_CALLABLE_REFERENCE!>!!<!>
        inv()
    }
}

fun poll71(): Flow<String> {
    return flow {
        val inv = ::bar2<!NOT_NULL_ASSERTION_ON_CALLABLE_REFERENCE!>!!<!>
        inv()
    }
}

fun poll72(): Flow<String> {
    return flow {
        val inv = ::bar3<!NOT_NULL_ASSERTION_ON_CALLABLE_REFERENCE!>!!<!>
        inv()
    }
}

fun poll73(): Flow<String> {
    return flow {
        val inv = ::bar4<!NOT_NULL_ASSERTION_ON_CALLABLE_REFERENCE!>!!<!>
        inv
    }
}

fun poll74(): Flow<String> {
    return flow {
        val inv = ::bar5<!NOT_NULL_ASSERTION_ON_CALLABLE_REFERENCE!>!!<!>
        inv
    }
}

fun poll75(): Flow<String> {
    return flow {
        val inv = ::Foo6<!NOT_NULL_ASSERTION_ON_CALLABLE_REFERENCE!>!!<!>
        inv
    }
}

fun poll76(): Flow<String> {
    return flow {
        val inv = <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::Foo7<!><!NOT_NULL_ASSERTION_ON_CALLABLE_REFERENCE!>!!<!>
        <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>inv<!>
    }
}

fun poll8(): Flow<String> {
    return flow {
        val inv = ::bar in setOf(::foo)
        <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>()
    }
}

fun poll81(): Flow<String> {
    return flow {
        val inv = ::bar2 <!TYPE_INFERENCE_ONLY_INPUT_TYPES_ERROR!>in<!> setOf(::foo2)
        <!DEBUG_INFO_MISSING_UNRESOLVED!>inv<!>()
    }
}

fun poll82(): Flow<String> {
    return flow {
        val inv = ::bar3 in setOf(::foo3)
        <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>inv<!>()
    }
}

fun poll83(): Flow<String> {
    return flow {
        val inv = ::bar4 <!TYPE_INFERENCE_ONLY_INPUT_TYPES_ERROR!>in<!> setOf(::foo4)
        <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>inv<!>
    }
}

fun poll84(): Flow<String> {
    return flow {
        val inv = ::bar5 in setOf(::foo5)
        inv
    }
}

fun poll85(): Flow<String> {
    return flow {
        val inv = ::Foo6 in setOf(::Foo6)
        inv
    }
}

fun poll86(): Flow<String> {
    return flow {
        val inv = ::Foo7 in <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>setOf<!>(<!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::Foo7<!>)
        <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>inv<!>
    }
}

fun poll87(): Flow<String> {
    return flow {
        val inv = <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::Foo7<!> <!TYPE_INFERENCE_ONLY_INPUT_TYPES_ERROR!>in<!> setOf(foo7())
        <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>inv<!>
    }
}

fun poll88(): Flow<String> {
    return flow {
        val inv = foo7() in <!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>setOf<!>(<!NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER!>::Foo7<!>)
        inv
    }
}

/* GENERATED_FIR_TAGS: asExpression, callableReference, capturedType, checkNotNullCall, classDeclaration,
equalityExpression, funWithExtensionReceiver, functionDeclaration, functionalType, getter, ifExpression, in,
integerLiteral, interfaceDeclaration, intersectionType, lambdaLiteral, localProperty, nullableType, out, outProjection,
primaryConstructor, propertyDeclaration, propertyWithExtensionReceiver, setter, smartcast, suspend, tryExpression,
typeParameter, typeWithExtension, vararg, whenExpression, whenWithSubject */
