package routes.subroutes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import dbmanagment.operation.ParametroOperation
import routes.exceptions
import utils.caseclass.CaseClassDB.Parametro
import utils.jsonformat.JsonFormats._

import scala.util.Success
object ParametroRoute {
  def getParametro(id: Int): Route =
    get {
      onComplete(ParametroOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllParametro: Route =
    post {
      onComplete(ParametroOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createParametro(): Route =
    post {
      entity(as[Parametro]) { order =>
        onComplete(ParametroOperation.insert(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
