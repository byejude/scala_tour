package com.tulip.oop

/** class、object、case class、case object区别
  *
  * class 类似Java中的class；
  * object Scala不能定义静态成员，用定义单例对象代之；
  * case class被称为样例类，是一种特殊的类，常被用于模式匹配。
  *
  * 一、class 和 object 关系：
  * 1.单例对象不能带参数，类可以
  * 2.对象可以和类名一样时，object被称为伴生对象，class被称为伴生类；
  * 3.类和伴生对象可以相互访问其私有属性，但是它们必须在一个源文件当中；
  * 4.类只会被编译，不会被执行。要执行，必须在Object中。
  *
  * 二、case class 与 class 区别：
  * 1.初始化的时候可以不用new，也可以加上，但是普通类必须加new;
  * 2.默认实现了equals、hashCode方法；
  * 3.默认是可以序列化的，实现了Serializable；
  * 4.自动从scala.Product中继承一些函数;
  * 5.case class 构造函数参数是public的，我们可以直接访问；
  * 6.case class默认情况下不能修改属性值；
  * 7.case class最重要的功能，支持模式匹配，这也是定义case class的重要原因。
  *
  * 三、case class 和 case object 区别：
  * 1.类中有参和无参，当类有参数的时候，用case class ，当类没有参数的时候那么用case object。
  *
  * 四、当一个类被声名为case class的时候，scala会帮助我们做下面几件事情：
  * 1.构造器中的参数如果不被声明为var的话，它默认的话是val类型的，但一般不推荐将构造器中的参数声明为var
  * 2.自动创建伴生对象，同时在里面给我们实现子apply方法，使得我们在使用的时候可以不直接显示地new对象
  * 3.伴生对象中同样会帮我们实现unapply方法，从而可以将case class应用于模式匹配，关于unapply方法我们在后面的“提取器”那一节会重点讲解
  * 4.实现自己的toString、hashCode、copy、equals方法
  * 除此之此，case class与其它普通的scala类没有区别
  */

case class Iteblog(name:String){
  def apply(name: String): Iteblog = {
    println("its just overrider the apply")
    new Iteblog(name)
  }
}
class Person1(name: String,age: Int){
//  //TODO  在类里面重写apply是没有用的
//  def apply(name: String, age: Int): Person1 = {
//    println("ts just overrider the apply "+name+age)
//    new Person1(name, age)
//  }

  def test(): Unit ={
    println(name+age)
  }

  //TODO 只能在类里面重载构造器this 而且左右只能是this 不能添加其他语句
  def this(name: String) = {
    this(name, 122)
  }
}

object Person1 {
  def apply(name: String, age: Int): Person1 = {
    println("its coming in apply")
    new Person1(name, age)
  }
}

object TestScala {

  def main(args: Array[String]): Unit = {

    val iteblog = new Iteblog("iteblog_hadoop")

    val iteblog2 = Iteblog("iteblog_hadoop")

    println(iteblog == iteblog2)

    println(iteblog.hashCode)

    println(iteblog2.hashCode)

    val person1 = new Person1("tulip")
    println(person1)

    println(Person1("tulip",12))
  }

}
