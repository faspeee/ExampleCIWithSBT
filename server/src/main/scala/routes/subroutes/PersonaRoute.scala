package routes.subroutes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import dbmanagment.operation.PersonaOperation
import routes.exceptions
import utils.caseclass.CaseClassDB.Persona
import utils.jsonformat.JsonFormats._

import scala.util.Success
object PersonaRoute {
  def getPersona(id: Int): Route =
    get {
      onComplete(PersonaOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllPersona: Route =
    post {
      onComplete(PersonaOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createPersona(): Route =
    post {
      entity(as[Persona]) { order =>
        onComplete(PersonaOperation.insert(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
  def createAllPersona(): Route =
    post {
      entity(as[List[Persona]]) { order =>
        onComplete(PersonaOperation.insertAll(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
  def deletePersona(): Route =
    post {
      entity(as[Persona]) { order =>
        onComplete(PersonaOperation.delete(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
  def deleteAllPersona(): Route =
    post {
      entity(as[List[Persona]]) { order =>
        onComplete(PersonaOperation.deleteAll(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
  def updatePersona(): Route =
    post {
      entity(as[Persona]) { order =>
        onComplete(PersonaOperation.update(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
