package com.tulip.implicits {
  package database_api {

    case class InvalidColumnName(name: String) extends RuntimeException(s"Invalid column name $name")

    trait Row {
      def getInt(colName: String): Int

      def getDouble(colName: String): Double

      def getText(colName: String): String
    }

  }

  package javadb {

    import database_api._

    case class JRow(representation: Map[String, Any]) extends Row {
      override def getInt(colName: String): Int = get(colName).asInstanceOf[Int]

      override def getDouble(colName: String): Double = get(colName).asInstanceOf[Double]

      override def getText(colName: String): String = get(colName).asInstanceOf[String]

      private def get(colName: String): Any =
        representation.getOrElse(colName, throw InvalidColumnName(colName))
    }

    object JRow {
      def apply(pairs: (String, Any)*) = new JRow(Map(pairs: _*))
    }

  }

  object implicits {

    import javadb.JRow

    //相当于包装了Javadb 而 不显式出现
    implicit class SRow(jrow: JRow) {
      //包装方法名需要一致
      def get[T](colName: String)(implicit toT: (JRow, String) => T): T =
        toT(jrow, colName)
    }

    def printColName(colName: String) = println(s"colname is $colName")

    implicit def jrowToxInt: (JRow, String) => Int =
      (jrow: JRow, colName: String) => {
        printColName(colName)
        jrow.getInt(colName)
      }

    //val 与 def 都视为隐式参数
    implicit val jrowToxDouble: (JRow, String) => Double =
      (jrow: JRow, colName: String) => jrow.getDouble(colName)

    implicit def jrowToxString: (JRow, String) => String =
      (jrow: JRow, colName: String) => jrow.getText(colName)
  }

  object DB {

    import implicits._

    def main(args: Array[String]) = {
      val row = javadb.JRow("one" -> 1, "two" -> 2.2, "three" -> "THREE!")
      //scala 会根据返回类型推断为使用哪一个隐式函数(参数)
      val oneValue1: Int = row.get("one")
      val twoValue1: Double = row.get("two")
      val threeValue1: String = row.get("three")
      // val fourValue1: Byte = row.get("four") // 不编译该行
      println(s"one1 -> $oneValue1")
      println(s"two1 -> $twoValue1")
      println(s"three1 -> $threeValue1")
      val oneValue2 = row.get[Int]("one")
      val twoValue2 = row.get[Double]("two")
      val threeValue2 = row.get[String]("three")
      // val fourValue2 = row.get[Byte]("four") // 不编译该行
      println(s"one2 -> $oneValue2")
      println(s"two2 -> $twoValue2")
      println(s"three2 -> $threeValue2")
    }
  }

}
