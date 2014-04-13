package dk.cirque.stereoswitch

import org.json4s._
import org.json4s.native.JsonMethods._

case class Connection(source: Int, destination: Int) { 

  override def toString = "Connection of source: " + source + ", destination: " + destination 

  def toJson = JObject(List(JField("source",JInt(source)),JField("destination",JInt(destination))))
}
