package com.tulip.concurrency.akka

import Messages._

import scala.util.{Try, Success, Failure}
import scala.util.control.NonFatal
import scala.concurrent.duration._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.{
  Actor, ActorLogging, ActorRef,
  ActorSystem, Props, OneForOneStrategy, SupervisorStrategy
}
import akka.pattern.ask
import akka.util.Timeout

/**
 * Author: Tulip
 * Date: 2020/1/5 19:50
 */
class ServerActor extends Actor with ActorLogging {

  implicit val timeout = Timeout(1.second)

  override def receive: Receive = initial

  override def supervisorStrategy: SupervisorStrategy = {
    val decider: SupervisorStrategy.Decider = {
      case WorkerActor.CrashException => SupervisorStrategy.Restart
      case NonFatal(_) => SupervisorStrategy.Resume
    }
    OneForOneStrategy()(decider orElse super.supervisorStrategy.decider)
  }

  var workers = Vector.empty[ActorRef]

  val initial: Receive = {
    case Start(numberOfWorkers) =>
      workers = ((1 to numberOfWorkers) map makeWorker).toVector
      context become processRequests
  }

  val processRequests: Receive = {
    case c@Crash(n) => workers(n % workers.size) ! c
    case DumpAll =>
      Future.traverse(workers)(_ ? DumpAll)
        .onComplete(askHandler("State of the workers"))
    case Dump(n) =>
      (workers(n % workers.size) ? DumpAll).map(Vector(_))
        .onComplete(askHandler(s"State of worker $n"))
    case request: KeyedRequest =>
      val key = request.key.toInt
      val index = key % workers.size
      workers(index) ! request
    case Response(Success(message)) => printResult(message)
    case Response(Failure(exception)) => printResult(s"Error! $exception")
  }

  def askHandler(prefix: String): PartialFunction[Try[Any], Unit] = {
    case Success(suc) => suc match {
      case vect: Vector[_] =>
        printResult(s"$prefix: \n")
        vect foreach {
          case Response(Success(message)) => printResult(s"$message")
          case Response(Failure(exception)) => printResult(s"ERROR! Success received wrapping! $exception")
        }

      case _ => printResult(s"BUG! Expected a vector,got $suc")
    }
    case Failure(ex) => printResult(s"ERROR! $ex")
  }

  protected def printResult(message: String) = {
    print(s"<<< $message")
  }

  protected def makeWorker(i: Int) =
    context.actorOf(Props[WorkerActor], s"worker -$i")

}

object ServerActor {
  def make(system: ActorSystem): ActorRef =
    system.actorOf(Props[ServerActor], "server")
}
