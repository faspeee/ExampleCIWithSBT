package routes.subroutes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, onComplete, post}
import akka.http.scaladsl.server.Route
import dbmanagment.operation.GiornoOperation
import utils.caseclass.CaseClassDB.Giorno

import utils.jsonformat.JsonFormats._
import scala.util.Success

object GiornoRoute {
  def getGiorno(id: Int): Route =
    get {
      onComplete(GiornoOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllGiorno: Route =
    post {
      onComplete(GiornoOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createGiorno(): Route =
    post {
      entity(as[Giorno]) { order =>
        onComplete(GiornoOperation.insert(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
