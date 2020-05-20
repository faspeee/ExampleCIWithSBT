package routes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import akka.http.scaladsl.server.Route
import dbmanagment.operation.TerminaleOperation
import utils.caseclass.CaseClassDB.Terminale

import scala.util.Success
import utils.jsonformat.JsonFormats._

object TerminaleRoute  {
  def getZona(id: Int): Route =
    get {
      onComplete(TerminaleOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllZona: Route =
    get {
      onComplete(TerminaleOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createZona(): Route =
    post {
      entity(as[Terminale]) { order =>
        onComplete(TerminaleOperation.insert(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
