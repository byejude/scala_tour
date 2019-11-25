package com.tulip.rounding

import scala.language.reflectiveCalls
import scala.io.Source
import scala.util.control.NonFatal

/**
 * Author: Tulip
 * Date: 2019/11/25 18:41
 */
object TryCatchArm {

  def countLines(fileName: String) = {
    println("**********************")
    manage(Source.fromFile(fileName)) {source =>
      val size = source.getLines().size
      println(s"file $fileName has $size lines")
      if (size < 20){
        throw new RuntimeException("too big!")
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val strArray:Array[String] = new Array[String](1)
    strArray.appended(".\\desktop.ini")
    strArray.foreach(arg => countLines(arg))
  }
}

object manage{
  def apply[R <: {def close():Unit},T](resource: => R)(f: R => T) = {
      var res: Option[R] = None
    try {
      res = Some(resource)
      f(res.get)
    }catch {
      case NonFatal(ex) => println(s"Non fatal exception! $ex")
    } finally {
      if (res != None){
        println(s"Closing resource...")
        res.get.close
      }
    }
  }
}
