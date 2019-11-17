package com.tulip.helloworld

object Helloworld{
  def main(args: Array[String]) = {
    val str = args.map(_.toUpperCase()).mkString("[","%","]")
    println(str)
  }
}