class ServiceImportante(name: String) {
  def work(i: Int): Int = {
    println(s"ServiceImportante: doing work! $i")
    i + 1
  }
}

trait Logging {
  def info(message: String): Unit

  def warning(message: String): Unit

  def error(message: String): Unit
}

trait StdoutLogging extends Logging {
  override def info(message: String): Unit = println(s"INFO: $message")

  override def warning(message: String): Unit = println(s"WARNING: $message")

  override def error(message: String): Unit = println(s"ERROR: $message")
}

val service = new ServiceImportante("dos") with StdoutLogging {
  override def work(i: Int): Int = {
    info(s"starting work: i = $i")
    val res = super.work(i)
    info(s"ending work : i = $i, result = $res")
    res
  }
}

(1 to 20 by 4) foreach (i => println(s"Result is ${service.work(i)}"))

