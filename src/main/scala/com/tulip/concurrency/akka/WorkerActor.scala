package com.tulip.concurrency.akka

import akka.actor.{Actor, ActorLogging}
import Messages._

import scala.util.{Success, Try}

/**
 * Author: Tulip
 * Date: 2020/1/5 19:07
 */
class WorkerActor extends Actor with ActorLogging{

  private val dataStore = collection.mutable.Map.empty[Long,String]

  override def receive: Receive = {
    case Create(key, value) =>
      dataStore += key->value
      sender ! Response(Success(s"$key -> $value,added!"))

    case Read(key) =>
      sender ! Response(Try(s"${dataStore(key)} found for key = $key"))

    case Update(key, value) =>
      dataStore += key -> value
      sender ! Response(Success(s"$key -> $value updated!"))

    case Delete(key) =>
      dataStore -= key
      sender ! Response(Success(s"$key deleted!"))

    case Crash(_) => throw WorkerActor.CrashException

    case DumpAll =>
      sender ! Response(Success(s"${self.path}: dataStore = $dataStore"))
  }

}

object WorkerActor {
  case object CrashException extends RuntimeException("Crash!")
}
