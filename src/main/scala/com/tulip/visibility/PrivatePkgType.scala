package scopeA {
  private[scopeA] class PrivateClassPkg1

  package scopeA2 {
    private [scopeA2] class PrivateClass2
    private [scopeA]  class PrivateClass3
  }

  class PrivateClassPkg4 extends PrivateClassPkg1
  protected class PrivateClassPkg5 extends PrivateClassPkg1
  private class PrivateClassPkg6 extends PrivateClassPkg1
  private[this] class PrivateClassPkg7 extends PrivateClassPkg1

  //private[this] class PrivateClass8 extends scopeA2.PrivateClass2 // ERROR
  private[this] class PrivateClassPkg9 extends scopeA2.PrivateClass3
}

package scopeB {
  //class PrivateClass1B extends scopeA.PrivateClass1 // ERROR
}
