package dk.cirque.stereoswitch

import akka.actor.ActorSystem
import akka.actor.Props
import akka.io.IO
import spray.can.Http
import dk.cirque.stereoswitch.SwitchActor

object Server extends App {

implicit val system = ActorSystem()

val listener = system.actorOf(Props[SwitchActor], "switch-service")

IO(Http) ! Http.Bind(listener, interface = "localhost", port = 8080)

}
