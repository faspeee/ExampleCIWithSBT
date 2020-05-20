package routes.subroutes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, onComplete, post}
import akka.http.scaladsl.server.Route
import dbmanagment.operation.ContrattoOperation
import utils.caseclass.CaseClassDB.Contratto
import utils.jsonformat.JsonFormats._
import scala.util.Success
import routes.exceptions
object ContrattoRoute {
  def getContratto(id: Int): Route =
    get {
      onComplete(ContrattoOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllContratto: Route =
    post {
      onComplete(ContrattoOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createContratto(): Route =
    post {
      entity(as[Contratto]) { order =>
        onComplete(ContrattoOperation.insert(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
