import scala.math.BigInt
//LazyList #::专属连接操作符
val fibs: LazyList[BigInt] = BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map (n => n._1 + n._2)
fibs take 30 foreach (i => print(s"$i "))