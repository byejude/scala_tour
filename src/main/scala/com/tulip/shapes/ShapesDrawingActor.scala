package com.tulip.shapes

import akka.actor.Actor
/**
 * Author: Tulip
 * Date: 2019/11/17 21:02
 */
object Messages {
  object Exit
  object Finished
  case class Response(message: String)
}

class ShapesDrawingActor extends Actor {
  import Messages._

  def receive = {
    case s: Shape =>
      s.draw(str => println(s"ShapesDrawingActor: $str"))
      sender ! Response(s"ShapesDrawingActor: $s drawn")
    case Exit =>
      println(s"ShapesDrawingActor: exiting...")
      sender ! Finished
    case unexpected =>  // default. Equivalent to "unexpected: Any"
      val response = Response(s"ERROR: Unknown message: $unexpected")
      println(s"ShapesDrawingActor: $response")
      sender ! response
  }
}
