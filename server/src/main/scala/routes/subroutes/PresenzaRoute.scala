package routes.subroutes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import dbmanagment.operation.PresenzaOperation
import routes.exceptions
import utils.caseclass.CaseClassDB.Presenza
import utils.jsonformat.JsonFormats._

import scala.util.Success
object PresenzaRoute {
  def getPresenza(id: Int): Route =
    get {
      onComplete(PresenzaOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllPresenza: Route =
    get {
      onComplete(PresenzaOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createPresenza(): Route =
    post {
      entity(as[Presenza]) { order =>
        onComplete(PresenzaOperation.insert(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
