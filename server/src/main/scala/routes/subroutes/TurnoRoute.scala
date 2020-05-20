package routes.subroutes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import dbmanagment.operation.TurnoOperation
import routes.exceptions
import utils.caseclass.CaseClassDB.Turno
import utils.jsonformat.JsonFormats._

import scala.util.Success
object TurnoRoute {
  def getTurno(id: Int): Route =
    get {
      onComplete(TurnoOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllTurno: Route =
    post {
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
  def createAllTurno(): Route =
    post {
      entity(as[List[Turno]]) { order =>
        onComplete(TurnoOperation.insertAll(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
  def deleteTurno(): Route =
    post {
      entity(as[Turno]) { order =>
        onComplete(TurnoOperation.delete(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
  def deleteAllTurno(): Route =
    post {
      entity(as[List[Turno]]) { order =>
        onComplete(TurnoOperation.deleteAll(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
  def updateTurno(): Route =
    post {
      entity(as[Turno]) { order =>
        onComplete(TurnoOperation.update(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
