package routes.subroutes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, onComplete, post}
import akka.http.scaladsl.server.Route
import dbmanagment.operation.GruppoTerminaleOperation
import utils.caseclass.CaseClassDB.GruppoTerminale
import utils.jsonformat.JsonFormats._

import scala.util.Success

object GruppoTerminaleRoute {
  def getGruppoTerminale(id: Int): Route =
    get {
      onComplete(GruppoTerminaleOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllGruppoTerminale: Route =
    post {
      onComplete(GruppoTerminaleOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createGruppoTerminale(): Route =
    post {
      entity(as[GruppoTerminale]) { order =>
        onComplete(GruppoTerminaleOperation.insert(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
