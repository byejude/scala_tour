package com.tulip.oop


case class Address(street: String, city: String, state: String, zip: String) {

  def this(zip: String) =
    this("[unknown]", Address.zipToCity(zip), Address.zipToState(zip), zip)
}

//伴生对象中的方法及apply 可以朝着java 静态类型角度去考虑  不过 apply是工厂会生成对象
object Address {

  def zipToCity(zip: String) = s"Anytown-$zip"

  def zipToState(zip: String) = s"CA-$zip"
}

case class Person(name: String, age: Option[Int], address: Option[Address]) {

  def this(name: String) = this(name, None, None)

  def this(name: String, age: Int) = this(name, Some(age), None)

  def this(name: String, age: Int, address: Address) =
    this(name, Some(age), Some(address))

  def this(name: String, address: Address) = this(name, None, Some(address))
}

object TestAuxConstructor {

  def main(args: Array[String]): Unit = {

    val a1 = new Address("1 Scala Lane", "Anytown", "CA", "98765")
    // Result: Address(1 Scala Lane,Anytown,CA,98765)


    val a2 = new Address("98765")
    // Result: Address([unknown],Anytown,CA,98765)

    println(new Person("Buck Trends1"))
    // Result: Person(Buck Trends1,None,None)


    println(new Person("Buck Trends2", Some(20), Some(a1)))
    // Result: Person(Buck Trends2,Some(20),
    //           Some(Address(1 Scala Lane,Anytown,CA,98765)))


    println(new Person("Buck Trends3", 20, a2))
    // Result: Person(Buck Trends3,Some(20),
    //           Some(Address([unknown],Anytown,CA,98765)))

    println(new Person("Buck Trends4", 20))
    // Result: Person(Buck Trends4,Some(20),None)

    //TODO case类会添加上toString
    // 因此结果是Person(Buck Trends4,Some(20),Some(Address([unknown],Anytown-98765,CA-98765,98765)))
    println(Person("Buck Trends4", Some(20), Some(a2)))
  }
}