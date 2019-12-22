package com.tulip.objectsystem

//scala.collection.immutable.HashMap中hashmap不可变，不能put！

import scala.collection.mutable.HashMap

/**
 * Author: Tulip
 * Date: 2019/12/19 22:21
 */
/**
 * scala中判断对象相等
 * 原则:
 * 如果两个对象相等,那么其hashcode必定相同,所以重写equals方法,要重写hashcode(默认情况下hashcode是根据内存地址计算出来的值)
 * 但如果hashcode相同(即使用 == 比较为true),却不能证明他们相等
 * 关于重写equals为什么需要重写hashCode请参考https://www.cnblogs.com/wang-meng/p/7501378.html
 *
 * 对于基本数据类型，== 在java和scala中均比较的是两个变量的值。
 *
 * 在scala中,对于引用对象，如果有一个对象为null,== 调用的是eq(比较对象的内存地址),如果均不为null,则调用equals,
 * 注意这个equals是调用java中的equals,所以默认比较的还是内存地址,综上 == 默认情况下在scala中依然比较的是内存地址
 * 此外scala中的String就是java中的String,所以在scala中,如对"ok" == "ok",调用的是java String的equals
 *
 * 而在java中,== 永远比较的是内存地址,与你是否重写该对象的equals无关
 *
 */

class Student(val name: String) {
  val age = 100;

  override def equals(obj: Any): Boolean = {
    if (!obj.isInstanceOf[Student]) {
      false;
    } else {
      val x = obj.asInstanceOf[Student];
      this.name == x.name; //这个地方也可以使用equals比较,这样写的话就是直接调用java String的equals了(当然现在也是,因为name是String)
    }
  }


  override def hashCode(): Int = {
    name.length();
  }


}

object Demo2 {

  val s1 = new Student("hello");
  val s2 = new Student("hello");


  //重写equals之前的测试
  println("ok" == "ok") //true,调用的是equals,但由于是String类型,其重写了equals方法,比较的是值
  println("ok" == null) //false,调用eq,比较的是内存地址
  //println(null == null) //true,调用eq,比较的是内存地址
  println("ok".equals("ok")); //true

  println(s1 == s2); //false,未重写equals,比较的地址


  //只重写equals的测试
  println(s1 == s2); //true,此时调用的是重写后的equals,比较的是name字符串是否相同,即调用java String的equals,比较的是值
  println(s1.equals(s2)) //true,也是调用其内部重写的equals

  println(s1.eq(s2)); //false, eq比较的是地址,而我们还没有重写eq方法


  /*而在java中, == 只比较内存地址
   String str1 = new String("hello");
       String str2 = new String("hello");
       System.out.println(s1 == s2);//false
  */

  //此时测试hashMap,即s1与s2相同,hashCode却不同,此时对于HashMap来说s1与s2是两个不同的对象
  println(s1.hashCode() == s2.hashCode()); //false
  val map = new HashMap[Student, Int]();
  map.put(s1, 100);
  map.put(s2, 200);
  println(map.get(s1)); //Some(100)
  println(map.get(s2)); //Some(200)

  //重写hashCode后重新测试,此时对于HashMap来说s1与s2是同一个对象,s2的值会覆盖s1的值
  println(map.get(s1)); //Some(200)
  println(map.get(s2)); //Some(200)


  def main(args: Array[String]): Unit = {

  }
}
