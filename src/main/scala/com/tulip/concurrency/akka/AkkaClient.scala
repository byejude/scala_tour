package com.tulip.concurrency.akka

import Messages._
import akka.actor.{ActorRef, ActorSystem}
import java.lang.{NumberFormatException => NFE}

/**
 * Author: Tulip
 * Date: 2020/1/5 21:07
 */
object AkkaClient {
  private var system: Option[ActorSystem] = None

  def main(args: Array[String]): Unit = {
    processArgs(args.toIndexedSeq)
    val sys = ActorSystem("AkkaClient")
    system = Some(sys)
    val server = ServerActor.make(sys)
    val numberOfWorkers =
      sys.settings.config.getInt("server.number-workers")
    server ! Start(numberOfWorkers)
    processInput(server)
  }

  private def processArgs(args: Seq[String]): Unit = args match {
    case Nil =>
    case ("-h" | "--help") +: _ => exit(help, 0)
    case head +: _ => exit(s"Unkown input $head! \n" + help, 1)
  }

  private def processInput(server: ActorRef): Unit = {
    val blankRE = """^\s*#?\s*$""".r
    val badCrashRE = """^\s*[Cc][Rr][Aa][Ss][Hh]\s*$""".r
    val crashRE = """^\s*[Cc][Rr][Aa][Ss][Hh]\s+(\d+)\s*$""".r
    val dumpRE = """^\s*[Dd][Uu][Mm][Pp](\s+\d+)?\s*$""".r
    val charNumberRE = """^\s*(\w)\s+(\d+)\s*$""".r
    val charNumberStringRE = """^\s*(\w)\s+(\d+)\s+(.+)$""".r

    def prompt() = print(">> ") // <10>
    def missingActorNumber() =
      println("Crash command requires an actor number.")

    def invalidInput(s: String) =
      println(s"Unrecognized command: $s")

    def invalidCommand(c: String): Unit =
      println(s"Expected 'c', 'r', 'u', or 'd'. Got $c")

    def expectedString(): Unit =
      println("Expected a string after the command and number")

    def unexpectedString(c: String, n: String): Unit =
      println(s"Extra arguments after command and number '$c $n'")

    def finished(): Nothing = exit("Goodbye!", 0)

    def handleInt[R](ns: String, context: String = "")(f: Int => R) =
      handleN(ns.toInt, ns, context)(f)

    def handleLong[R](ns: String, context: String = "")(f: Long => R) =
      handleN(ns.toLong, ns, context)(f)

    def handleN[N: Numeric, R](n: => N, ns: String, context: String)(f: N => R) =
      try {
        f(n)
      } catch {
        case _: NFE =>
          val s = if (context.size > 0) {
            s"""With context "$context", expected a number, but got $ns"""
          } else {
            s"""Expected a number, but got $ns"""
          }
          println(s)
      }

    val handleLine: PartialFunction[String, Unit] = {
      case blankRE() => /* do nothing */
      case "h" | "help" => println(help)
      case dumpRE(n) =>
        server ! (if (n == null) DumpAll else handleInt(n.trim)(Dump.apply))
      case badCrashRE() => missingActorNumber()
      case crashRE(n) => server ! handleInt(n)(Crash.apply)
      case charNumberStringRE(c, n, s) => c match {
        case "c" | "C" => server ! handleLong(n)(i => Create(i, s))
        case "u" | "U" => server ! handleLong(n)(i => Update(i, s))
        case "r" | "R" => unexpectedString(c, n)
        case "d" | "D" => unexpectedString(c, n)
        case _ => invalidCommand(c)
      }
      case charNumberRE(c, n) => c match {
        case "r" | "R" => server ! handleLong(n)(Read.apply)
        case "d" | "D" => server ! handleLong(n)(Delete.apply)
        case "c" | "C" => expectedString
        case "u" | "U" => expectedString
        case _ => invalidCommand(c)
      }
      case "q" | "quit" | "exit" => finished()
      case string => invalidInput(string)
    }

    while (true) {
      prompt()
      Console.in.readLine() match {
        case null => finished()
        case line => handleLine(line)
      }
    }
  }

  private val help =
    """Usage: AkkaClient [-h | --help]
      |Then, enter one of the following commands, one per line:
      |  h | help      Print this help message.
      |  c n string    Create "record" for key n for value string.
      |  r n           Read record for key n. It's an error if n isn't found.
      |  u n string    Update (or create) record for key n for value string.
      |  d n           Delete record for key n. It's an error if n isn't found.
      |  crash n       "Crash" worker n (to test recovery).
      |  dump [n]      Dump the state of all workers (default) or worker n.
      |  ^d | quit     Quit.
      |""".stripMargin

  private def exit(message: String, status: Int): Nothing = {
    for (sys <- system) {
      sys.terminate()
    }
    println(message)
    sys.exit(status)
  }
}
