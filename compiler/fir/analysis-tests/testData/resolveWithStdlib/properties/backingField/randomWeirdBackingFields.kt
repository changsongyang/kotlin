// RUN_PIPELINE_TILL: FRONTEND
abstract class Some1 {
    abstract val foo: List<Int>
        internal <!EXPLICIT_BACKING_FIELD_IN_ABSTRACT_PROPERTY!>field<!> = mutableListOf<Int>()
}

/* GENERATED_FIR_TAGS: classDeclaration, propertyDeclaration */
