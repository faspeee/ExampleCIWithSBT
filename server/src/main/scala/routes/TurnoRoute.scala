package routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import akka.http.scaladsl.server.Route
import dbmanagment.operation.TurnoOperation
import utils.caseclass.CaseClassDB.Turno
import utils.jsonformat.JsonFormats._
import scala.util.Success
import exceptions.Exceptions._

object TurnoRoute {
  def getTurno(id: Int): Route =
    get {
      onComplete(TurnoOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllTurno: Route =
    get {
      onComplete(TurnoOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createTurno(): Route =
    post {
      entity(as[Turno]) { turno =>
        onComplete(TurnoOperation.insert(turno)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
