class C1 {
  def m = print("C1 ")
}

trait T1 extends C1 {
  override def m = {
    print("T1 ");
    super.m
  }
}

trait T2 extends C1 {
  override def m = {
    print("T2 ");
    super.m
  }
}

trait T3 extends C1 {
  override def m = {
    print("T3 ");
    super.m
  }
}

class C2 extends T1 with T2 with T3 {
  override def m = {
    print("C2 ");
    super.m
  }
}

val c2 = new C2
c2.m
//result: C2 T3 T2 T1 C1
//TODO 调用方法按照继承最右至左原则