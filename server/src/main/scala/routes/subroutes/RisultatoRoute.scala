package routes.subroutes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import dbmanagment.operation.RisultatoOperation
import routes.exceptions
import utils.caseclass.CaseClassDB.Risultato
import utils.jsonformat.JsonFormats._

import scala.util.Success
object RisultatoRoute {
  def getRisultato(id: Int): Route =
    get {
      onComplete(RisultatoOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllRisultato: Route =
    get {
      onComplete(RisultatoOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createRisultato(): Route =
    post {
      entity(as[Risultato]) { order =>
        onComplete(RisultatoOperation.insert(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
