package dk.cirque.stereoswitch.endpoint

import org.json4s._
import org.json4s.native.JsonMethods._

abstract class Endpoint {
  val name: String
  override def toString = name
  def toJson: JValue
}

case class Input(name: String) extends Endpoint {
  override def toJson = JObject(JField("input",JObject(JField("name",JString(name)))))
}

case class Output(name: String) extends Endpoint {

  override def toJson = JObject(JField("input",JObject(JField("name",JString(name)))))
}
