// RUN_PIPELINE_TILL: BACKEND
// DUMP_CFG

interface FirBase
interface FirFunctionCall : FirBase


fun foo(statements: List<FirBase>, arguments: List<FirBase>, explicitReceiver: FirBase): List<FirFunctionCall> {

    val firstCalls = with(statements.last() as FirFunctionCall) setCall@{
        buildList {
            add(this@setCall)
            with(arguments.last() as FirFunctionCall) plusCall@{
                add(this@plusCall)
                add(explicitReceiver as FirFunctionCall)
            }
        }
    }

    return firstCalls
}

/* GENERATED_FIR_TAGS: asExpression, functionDeclaration, interfaceDeclaration, lambdaLiteral, localProperty,
propertyDeclaration, thisExpression */
