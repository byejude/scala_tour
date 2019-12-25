import scala.language.implicitConversions

object Serialization {

  case class Writable(value1: Any, value2: Any) {
    def serialized: String = s"-- $value1 --$value2"
  }

  implicit def fromInt(i: Int, q: Int) = Writable(i, q)
  implicit def fromTuple(t:Tuple2[Int,Int]) = Writable(t._1, t._2)

 // implicit def fromFloat(f: Float, q: Float) = Writable(f, q)

 // implicit def fromString(s: String, q: String) = Writable(s, q)
}

import Serialization._

object RemoteConnection {
  //以下签名等价于def write[T](implicit t: Writable): ReturnType = {...}
  def write[T <% Writable](t: T): Unit =
    println(t.serialized) // Use stdout as the "remote connection"
}

//todo 多参数时需要声明视图边界对象
RemoteConnection.write(Writable(100,100)) // Prints -- 100 --
RemoteConnection.write(100,100)
//RemoteConnection.write(3.14f, 3.14f) // Prints -- 3.14 --
//RemoteConnection.write("hello!", "hello!") // Prints -- hello! --
