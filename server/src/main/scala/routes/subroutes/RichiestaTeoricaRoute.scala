package routes.subroutes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import dbmanagment.operation.RichiestaTeoricaOperation
import routes.exceptions
import utils.caseclass.CaseClassDB.RichiestaTeorica
import utils.jsonformat.JsonFormats._

import scala.util.Success
object RichiestaTeoricaRoute {
  def getRichiestaTeorica(id: Int): Route =
    get {
      onComplete(RichiestaTeoricaOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllRichiestaTeorica: Route =
    post {
      onComplete(RichiestaTeoricaOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createRichiestaTeorica(): Route =
    post {
      entity(as[RichiestaTeorica]) { richiestaTeorica =>
        onComplete(RichiestaTeoricaOperation.insert(richiestaTeorica)) {
          case Success(t) =>  complete(StatusCodes.Created)
        }
      }
    }
}