package com.tulip.oop

/**
 * Author: Tulip
 * Date: 2019/12/12 21:39
 */
case class ZipCode(zip: String, extension: Option[String] = None) {
  //TODO 初始化移到对象的顶部,因为它不引用其他任何内容.
  // 这样,它将始终首先被初始化.不然会报scala.UninitializedFieldError
  protected val zipRE = """(\d){5}""".r
  protected val extRE = """(\d){4}""".r
  require(validUSPS(zip, extension), // <1>
    s"Invalid Zip+4 specified: $toString")

  /**
   * Is it a real US Postal Service zip code?
   */
  protected def validUSPS(z: String, e: Option[String]): Boolean = // <2>
    (z, e) match {
      case (zipRE(_), None) => true
      case (zipRE(_), Some(extRE(_))) => true
      case (_, _) => false
    }

  override def toString =
    if (extension != None) s"$zip-${extension.get}" else zip.toString
}

object ZipCode {
  def apply(zip: String, extension: String): ZipCode =
    new ZipCode(zip, Some(extension))
}

object ZipCodeTest {
  def main(args: Array[String]): Unit = {
    println(ZipCode("12345"))
    // Result: ZipCode = 12345

    println(ZipCode("12345", Some("6789")))
    // Result: ZipCode = 12345-6789

    println(ZipCode("12345", "6789"))
    // Result: ZipCode = 12345-6789

    Seq("0", "1", "12", "123", "1234", "123456",
      "1234e", "123d5", "12c45", "1b345", "a2345") foreach { z =>
      try {
        println(ZipCode(z, "6789"))
      } catch {
        case e: java.lang.IllegalArgumentException => // expected
      }
      try {
        println(ZipCode(z))
      } catch {
        case e: java.lang.IllegalArgumentException => // expected
      }
    }

    Seq("0", "1", "12", "123", "12345",
      "123d", "12c4", "1b34", "a234") foreach { e =>
      try {
        println(ZipCode("12345", e)) // Invalid Zip+4 specified: 12345-0
      } catch {
        case e: java.lang.IllegalArgumentException => // expected
      }

    }
  }
}

