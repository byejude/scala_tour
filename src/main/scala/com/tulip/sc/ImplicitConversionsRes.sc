import scala.language.implicitConversions

case class Foo(s: String)

object Foo {

  implicit def fromString(s: String): Foo = Foo(s + "test")

  implicit def sad(s: Int): Foo = Foo(s + "sad")
}

// it will override the fromString
implicit def OverrideFromString(s: String): Foo = Foo(s+"Override")

class Test {
  def m1(foo: Foo) = println(foo.toString)

  def m(s: String) = m1(s)
}


val x = new Test
x.m("1234123412")

