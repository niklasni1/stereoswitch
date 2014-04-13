package dk.cirque.stereoswitch

import org.json4s._
import org.json4s.native.JsonMethods._

import dk.cirque.stereoswitch.Connection
import dk.cirque.stereoswitch.endpoint.Endpoint
import dk.cirque.stereoswitch.endpoint._

class UnknownEndpointException extends Exception

object SwitchLibrary {
  private var connection = new Connection(0,0)

  val sources = List(Input("Radio"),Input("iPod"))
  val destinations = List(Output("Kitchen"),Output("Bedroom"))

  def getEndpoints = (sources,destinations)

  def getEndpointsAsJson = JObject(List(getSourcesAsJson,getDestinationsAsJson))

  def getSources = sources
  def getSourcesAsJson = JField("sources",JArray(sources.map(_.toJson)))

  def getDestinations = destinations

  def getDestinationsAsJson = JField("destinations",JArray(destinations.map(_.toJson)))

  def makeConnection(s: Int, d: Int) = 
  if((sources.size > s) && (destinations.size > d)) { 
      connection = new Connection(s,d)
    }
    else throw new UnknownEndpointException

  def getConnection =
    connection
}
