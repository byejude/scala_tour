import scala.collection.parallel.CollectionConverters._

object  Parallel {
  def main(args: Array[String]): Unit = {
    var list = ((1 to 10) fold "") ((s1, s2) => s"$s1 - $s2")
    println(list)

    // Result: " - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10"
    list = ((1 to 10).par fold "") ((s1, s2) => s"$s1 - $s2")
    println(list)
    // Results for two runs:
    // " - 1 -  - 2 -  - 3 - 4 - 5 -  - 6 -  - 7 -  - 8 -  - 9 -  - 10"
    // " - 1 -  - 2 -  - 3 -  - 4 - 5 -  - 6 -  - 7 -  - 8 - 9 - 10"
    //" - 1 -  - 2 -  - 3 -  - 4 -  - 5 -  - 6 -  - 7 -  - 8 -  - 9 -  - 10"
    //" - 1 -  - 2 -  - 3 -  - 4 -  - 5 -  - 6 -  - 7 -  - 8 -  - 9 -  - 10"
    val listPar = List(1,2,3,4,5,6)


    var i = 0
    while( i < 20 ) {
      i += 1
      println(listPar.par.fold(10)(_ + _))
    }
    //TODO 与内核有关，我最高81，即6个线程在运行，6*10+6!

    list = ((1 to 10).par fold 0) ((s1, s2) => s1 + s2)
    // Result: 55
    println(list)
  }
}