package scopeA {
  class PrivateClassNested1 {
    class Nested {
      private[PrivateClassNested1] val nestedField = 1
    }

    private[PrivateClassNested1] val nested = new Nested
    val nestedNested = nested.nestedField
  }

  class PrivateClassNested2 extends PrivateClassNested1 {
    //val nField = new Nested().nestedField   // ERROR  //TODO private[T] 限定在T类的使用域里
  }

  class PrivateClassNested3 {
    val privateClass1 = new PrivateClassNested1
    //val privateNField = privateClass1.nested.nestedField // ERROR
  }
}