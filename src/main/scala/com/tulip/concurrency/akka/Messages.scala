package com.tulip.concurrency.akka

import scala.util.Try

/**
 * Author: Tulip
 * Date: 2020/1/3 22:17
 */
object Messages {
  sealed trait KeyedRequest {
    val key: Long
  }

  case class Create(key: Long,value: String) extends KeyedRequest
  case class Read(key: Long) extends KeyedRequest
  case class Update(key: Long, value: String) extends KeyedRequest
  case class Delete(key: Long) extends KeyedRequest

  case class Response(result:Try[String])

  case class Start(numberOfWorkers: Int = 1)
  case class Crash(whichOne: Int)
  case class Dump(which: Int)
  case object DumpAll
}
