package com.tulip.dynamic

/**
 * Author: Tulip
 * Date: 2020/1/5 22:42
 */

import scala.language.dynamics // <1>

case class CLINQ[T](records: Seq[Map[String, T]]) extends Dynamic {

  def selectDynamic(name: String): CLINQ[T] = // <2>
    if (name == "all" || records.length == 0) this // <3>
    else {
      val fields = name.split("_and_") // <4>
      val seed = Seq.empty[Map[String, T]]
      val newRecords = (records foldLeft seed) {
        (results, record) =>
          val projection = record filter { // <5>
            case (key, _) => fields contains key
          }
          // Drop records with no projection.
          if (projection.size > 0) results :+ projection
          else results
      }
      CLINQ(newRecords)
    }

  def applyDynamic(name: String)(field: String): Where = name match {
    case "where" => new Where(field)
    case _ => throw CLINQ.BadOperation(field, """Expected "where".""")
  }

  protected class Where(field: String) extends Dynamic {
    def filter(op: T => Boolean): CLINQ[T] = {
      val newRecords = records filter {
        _ exists {
          case (k, v) => field == k && op(v)
        }
      }
      CLINQ(newRecords)
    }

    def applyDynamic(op: String)(value: T): CLINQ[T] = op match {
      case "EQ" => filter(value == _)
      case "NE" => filter(value != _)
      case _ => throw CLINQ.BadOperation(field, """Expected "EQ" or "NE".""")
    }
  }

  override def toString: String = records mkString "\n"
}

object CLINQ {

  case class BadOperation(name: String, msg: String) extends RuntimeException(
    s"Unrecognized operation $name. $msg")

}

