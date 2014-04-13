package dk.cirque.stereoswitch

import akka.actor.Actor
import spray.routing._
import spray.http._
import spray.http.StatusCodes._
import MediaTypes._
import org.json4s.JsonAST._
import org.json4s.native.JsonMethods._

import spray.httpx.unmarshalling._
import spray.httpx.marshalling._

import dk.cirque.stereoswitch.SwitchLibrary
import dk.cirque.stereoswitch.UnknownEndpointException
import dk.cirque.stereoswitch.Connection
import dk.cirque.stereoswitch.ConnectionMarshalling._

class SwitchActor extends Actor with SwitchService {
  def actorRefFactory = context

  def receive = runRoute(switchRoute)
}

trait SwitchService extends HttpService {
  val switchRoute =
  path("connection") {
    get {
      respondWithMediaType(`application/json`) {
        complete {
          compact(render(SwitchLibrary.getConnection.toJson))
        }
      }
    } ~
    put {
      entity(as[Connection]) { c =>
      try {
        SwitchLibrary.makeConnection(c.source,c.destination);
        complete { "" }
      } catch {
        case e: UnknownEndpointException =>
          respondWithStatus(UnprocessableEntity) {
            complete { "" }
          }
        }
      }
    } 
  } ~
  pathPrefix("endpoints") {
    pathEnd {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            compact(render(SwitchLibrary.getEndpointsAsJson))
          }
        }
      }
    } ~
    pathPrefix("sources") {
      pathEnd {
        get {
          respondWithMediaType(`application/json`) {
            complete {
              compact(render(JObject(SwitchLibrary.getSourcesAsJson)))
            } 
          }
        }
      } ~
      path(IntNumber) { n =>
        get {
          try {
            val s = SwitchLibrary.getSources(n)
            respondWithMediaType(`application/json`) {
              complete {
                compact(render(s.toJson))
              }
            }
          } 
          catch {
            case e: IndexOutOfBoundsException =>
            respondWithStatus(NotFound) {
              complete { " " }
            } 
          }
        }
      }
    } ~
    pathPrefix("destinations") {
      pathEnd {
        get {
          respondWithMediaType(`application/json`) {
            complete {
              compact(render(JObject(SwitchLibrary.getSourcesAsJson)))
            } 
          }
        }
      } ~
      path(IntNumber) { n =>
        get {
          try {
            val d = SwitchLibrary.getDestinations(n)
            respondWithMediaType(`application/json`) {
              complete {
                compact(render(d.toJson))
              }
            }
          } 
          catch {
            case e: IndexOutOfBoundsException =>
            respondWithStatus(NotFound) {
              complete { " " }
            } 
          }
        }
      } 
    }
  }
}
