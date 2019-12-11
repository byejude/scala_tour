package com.tulip.oop

case class Person3(
                    name: String,
                    age: Option[Int] = None,
                    address: Option[Address] = None)

object Person3 {

  //TODO 由于我们重载的是普通方法，而不是构造器，编译器不会自动为 case 类的次级构造器创建 apply 方法,所以必须手动重载
  // 所以必须明确地指定返回类型，在这里返回类型是Person3。
  def apply(name: String): Person3 = new Person3(name)

  def apply(name: String, age: Int): Person3 = new Person3(name, Some(age))

  def apply(name: String, age: Int, address: Address): Person3 =
    new Person3(name, Some(age), Some(address))

  def apply(name: String, address: Address): Person3 =
    new Person3(name, address = Some(address))
}

object PersonAuxApply{
  def main(args: Array[String]): Unit = {
    val a1 = new Address("1 Scala Lane", "Anytown", "CA", "98765")
    val a2 = new Address("98765")
    //同样的是case以自动实现toString 所以不会打印出对象来
    Person3("Buck Trends1") // 主
    // 结果: Person3(Buck Trends1,None,None)
    Person3("Buck Trends2", Some(20), Some(a1)) // 主
    // 结果: Person3(Buck Trends2,Some(20),Some(Address(1 Scala Lane,Anytown,CA,98765)))
    Person3("Buck Trends3", 20, a1)
    // 结果: Person3(Buck Trends3,Some(20),
    // Some(Address(1 Scala Lane,Anytown,CA,98765)))
    Person3("Buck Trends4", Some(20)) // 主
    // 结果: Person3(Buck Trends4,Some(20),None)
    Person3("Buck Trends5", 20)
    // 结果: Person3(Buck Trends5,Some(20),None)
    Person3("Buck Trends6", address = Some(a2)) // 主
    // 结果: Person3(Buck Trends6,None,
    // Some(Address([unknown],Anytown,CA,98765)))
    Person3("Buck Trends7", address = a2)
    // 结果: Person3(Buck Trends7,None,
    // Some(Address([unknown],Anytown,CA,98765)))
  }
}

