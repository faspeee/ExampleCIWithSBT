package routes.subroutes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import dbmanagment.operation.TerminaleOperation
import routes.exceptions
import utils.caseclass.CaseClassDB.Terminale
import utils.jsonformat.JsonFormats._

import scala.util.Success
object TerminaleRoute  {
  def getTerminale(id: Int): Route =
    get {
      onComplete(TerminaleOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllTerminale: Route =
    post {
      onComplete(TerminaleOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createTerminale(): Route =
    post {
      entity(as[Terminale]) { order =>
        onComplete(TerminaleOperation.insert(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
  def createAllTerminale(): Route =
    post {
      entity(as[List[Terminale]]) { order =>
        onComplete(TerminaleOperation.insertAll(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
  def deleteTerminale(): Route =
    post {
      entity(as[Terminale]) { order =>
        onComplete(TerminaleOperation.delete(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
  def deleteAllTerminale(): Route =
    post {
      entity(as[List[Terminale]]) { order =>
        onComplete(TerminaleOperation.deleteAll(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
  def updateTerminale(): Route =
    post {
      entity(as[Terminale]) { order =>
        onComplete(TerminaleOperation.update(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
