package dk.cirque.stereoswitch

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._
import org.json4s.JsonDSL._

import dk.cirque.stereoswitch.Library

class SwitchActor extends Actor with SwitchService {
  def actorRefFactory = context

  def receive = runRoute(switchRoute)
}

trait SwitchService extends HttpService {
  val switchRoute =
    path("") {
      get {
        respondWithMediaType(`text/plain`) { 
          complete {
            "hello GET"
          }
        }
      }
    } ~
    pathPrefix("number" / IntNumber) { num => pathEnd {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            "hello " + num + "\n" + 
            "Library t is " + Library.t
          }
        }
      }
    }
  }
}
