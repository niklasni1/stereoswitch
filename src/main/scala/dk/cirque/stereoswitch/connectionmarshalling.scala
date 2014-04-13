package dk.cirque.stereoswitch

import dk.cirque.stereoswitch.Connection

import org.json4s._
import org.json4s.native.JsonMethods._

import spray.httpx.unmarshalling._
import spray.httpx.marshalling._

import spray.http._
import spray.http.HttpEntity._
import spray.http.ContentTypeRange._
import spray.http.MediaTypes._

trait ConnectionMarshalling { 
  implicit val formats = DefaultFormats

  implicit val ConnectionUnmarshaller = Unmarshaller[Connection](`application/json`) { 
    case HttpEntity.NonEmpty(contentType, data) => 
      parse(data.asString).extract[Connection]
  }

  //implicit val ConnectionMarshaller = Marshaller.of[Connection]
}

object ConnectionMarshalling extends ConnectionMarshalling
