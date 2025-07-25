// RUN_PIPELINE_TILL: BACKEND
// FIR_IDENTICAL
import java.util.Enumeration

fun <T> java.util.Enumeration<T>.iterator() = object: Iterator<T> {
  override fun hasNext(): Boolean = hasMoreElements()

  override fun next() = nextElement()
}

interface MyIterator<T> {

  operator fun hasNext() : Boolean

  operator fun next() : T
}

operator fun <T : Any> T?.iterator() = object : MyIterator<T> {
    var hasNext = this@iterator != null
      private set
    override fun hasNext() = hasNext

    override fun next() : T {
        if (hasNext) {
            hasNext = false
            return this@iterator!!
        }
        throw java.util.NoSuchElementException()
    }
}

fun main() {
  val i : Int? = 1
  for (x in i) {
    System.out.println(x)
  }
}

/* GENERATED_FIR_TAGS: anonymousObjectExpression, assignment, checkNotNullCall, equalityExpression, flexibleType,
forLoop, funWithExtensionReceiver, functionDeclaration, ifExpression, integerLiteral, interfaceDeclaration, javaFunction,
javaProperty, localProperty, nullableType, operator, override, propertyDeclaration, thisExpression, typeConstraint,
typeParameter */
