package routes

import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import utils.jsonformat.JsonFormats._
object TurnoRoute {
  final case class Turno(name:String,orario:String)
  def getTurno(id: Int): Route =
    get {
      complete {
        "Gettando Turno " + id
      }
    }
  def createTurno(): Route =
    post {
      entity(as[Turno]) { order =>
        complete {
          "Received POST request for Turno " + order
        }
      }
    }
}
