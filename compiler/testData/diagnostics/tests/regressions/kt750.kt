// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
//KT-750 Type inference failed: Constraint violation
fun main() {
  var i : Int? = Integer.valueOf(100)
  var s : Int? = Integer.valueOf(100)

  val o = i.sure() + s.sure()
  System.out.println(o)
}

fun <T : Any> T?.sure() : T = this!!

/* GENERATED_FIR_TAGS: additiveExpression, checkNotNullCall, flexibleType, funWithExtensionReceiver, functionDeclaration,
integerLiteral, javaFunction, javaProperty, localProperty, nullableType, propertyDeclaration, thisExpression,
typeConstraint, typeParameter */
