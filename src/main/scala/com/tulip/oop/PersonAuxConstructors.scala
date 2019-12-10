package com.tulip.oop


case class Address(street: String, city: String, state: String, zip: String) {

  def this(zip: String) =
    this("[unknown]", Address.zipToCity(zip), Address.zipToState(zip), zip)
}

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
