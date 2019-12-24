package com.tulip.visibility

package scopeA {

  class PrivateClass1This(private[this] val privateField1This: Int) {
    //private[this] val privateField2 = 1
    if (privateField1This != 1) {
      println(privateField1This)
    }
    /*
    *todo  private[this] 只能在类内部使用，对象都不能直接使用,包括伴生类
    * this 也只能作用于包含该类型的包中
    */

    //    def equalFields(other: PrivateClass1) =
    //      (privateField1 == other.privateField1) && // 错误
    //        (privateField2 == other.privateField2) && // 错误
    //        (nested == other.nested) // 错误
    //    class Nested {
    //      private[this] val nestedField = 1
    //    }
    //    private[this] val nested = new Nested
    //  }
    //  class PrivateClass2 extends PrivateClass1(1) {
    //    val field1 = privateField1 // 错误
    //    val field2 = privateField2 // 错误
    //    val nField = new Nested().nestedField // 错误
    //  }
    //  class PrivateClass3 {
    //    val privateClass1 = new PrivateClass1(1)
    //    val privateField1 = privateClass1.privateField1 // 错误
    //    val privateField2 = privateClass1.privateField2 // 错误
    //    val privateNField = privateClass1.nested.nestedField // 错误
    //  }
  }

}

package scopeA {

  private[this] class PrivateClass1Thisx
  package scopeA2 {

    private[this] class PrivateClass2Thisx

  }

  //  class PrivateClass3 extends PrivateClass1 // 错误
  //  protected class PrivateClass4 extends PrivateClass1 // 错误
  //  private class PrivateClass5 extends PrivateClass1
  //  private[this] class PrivateClass6 extends PrivateClass1
  //  private[this] class PrivateClass7 extends scopeA2.PrivateClass2 // 错误
  //}
  //package scopeB {
  //  class PrivateClass1B extends scopeA.PrivateClass1 // 错误
  //}
}