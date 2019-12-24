package scopeA {
  class Class1 {
    private[scopeA] val scopeA_privateField = 1
    protected[scopeA] val scopeA_protectedField = 2
    private[Class1] val class1_privateField = 3
    protected[Class1] val class1_protectedField = 4
    private[this] val this_privateField = 5
    protected[this] val this_protectedField = 6
  }
  class Class2 extends Class1 {
    val field1 = scopeA_privateField             //TODO  private[scopeA] 保护本包不带继承
    val field2 = scopeA_protectedField          //TODO  protected[scopeA] 保护本包带继承
    val field3 = class1_privateField // 错误    //TODO  private[Class1]  保护本类不带继承
    val field4 = class1_protectedField         //TODO  protected[Class1] 保护本类带继承
    //val field5 = this_privateField // 错误   //TODO  private[this] 保护本类不带继承
    val field6 = this_protectedField          //TODO protected[this] 保护本类以及继承
  }
}
package scopeB {
  class Class2B extends scopeA.Class1 {
    //val field1 = scopeA_privateField // 错误
    val field2 = scopeA_protectedField
    val field3 = class1_privateField // 错误
    val field4 = class1_protectedField
    //val field5 = this_privateField // 错误
    val field6 = this_protectedField
  }
}