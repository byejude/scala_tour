package com.tulip.oop

/**
 * Author: Tulip
 * Date: 2019/12/14 20:55
 */
case class OfficeAddress(street: String, city: String, state: String, zip: String)

object OfficeAddress {
  def apply(zip: String) =
    new OfficeAddress(
      "[unknown]", OfficeAddress.zipToCity(zip), OfficeAddress.zipToState(zip), zip)

  def zipToCity(zip: String) = s"Anytown-$zip"

  def zipToState(zip: String) = s"CA-$zip"
}

trait HumanState {
  val name: String
  val age: Option[Int]
  val OfficeAddress: Option[OfficeAddress]


}

case class Human(
                   name: String,
                   age: Option[Int] = None,
                   OfficeAddress: Option[OfficeAddress] = None) extends HumanState

trait EmployeeState {
  val title: String
  val manager: Option[Employee]
}

case class Employee(
                     name: String,
                     age: Option[Int] = None,
                     OfficeAddress: Option[OfficeAddress] = None,
                     title: String = "[unknown]",
                     manager: Option[Employee] = None)
  extends HumanState with EmployeeState
