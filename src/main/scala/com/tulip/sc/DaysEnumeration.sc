

object WeekDay extends Enumeration {
  type WeekDay = Value
  val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
}

import WeekDay._

def isWorkingDay(d: WeekDay): Boolean = !(d == Sat || d == Sun)

WeekDay.values.filter(values => isWorkingDay(values)).foreach(values => println(s"id is ${values.id} and $values"))

WeekDay.values filter isWorkingDay foreach println