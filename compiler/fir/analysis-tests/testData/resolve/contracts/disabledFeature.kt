// RUN_PIPELINE_TILL: FRONTEND
// LANGUAGE: -ContractSyntaxV2
// LANGUAGE: -AllowContractsOnPropertyAccessors
// WITH_STDLIB

import kotlin.contracts.*

inline fun <reified T> requreIsInstance(value: Any) contract <!UNSUPPORTED_FEATURE!>[
    returns() implies (value is T)
]<!> {
    if (value !is T) throw IllegalArgumentException()
}

val Any?.myLength: Int?
    get() contract <!CONTRACT_NOT_ALLOWED, UNSUPPORTED_FEATURE!>[
        returnsNotNull() implies (this@length is String)
    ]<!> = (this as? String)?.length

fun test_1(x: Any) {
    requreIsInstance<String>(x)
    x.length
}

fun test_2(x: Any) {
    if (x.myLength != null) {
        x.<!UNRESOLVED_REFERENCE!>length<!>
    }
}

/* GENERATED_FIR_TAGS: contractConditionalEffect, contracts, equalityExpression, functionDeclaration, getter,
ifExpression, inline, isExpression, nullableType, propertyDeclaration, propertyWithExtensionReceiver, reified, safeCall,
smartcast, thisExpression, typeParameter */
