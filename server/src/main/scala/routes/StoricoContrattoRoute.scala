package routes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import akka.http.scaladsl.server.Route
import dbmanagment.operation.StoricoContrattoOperation
import utils.caseclass.CaseClassDB.StoricoContratto

import scala.util.Success
import utils.jsonformat.JsonFormats._
object StoricoContrattoRoute  {
  def getZona(id: Int): Route =
    get {
      onComplete(StoricoContrattoOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllZona: Route =
    get {
      onComplete(StoricoContrattoOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createZona(): Route =
    post {
      entity(as[StoricoContratto]) { order =>
        onComplete(StoricoContrattoOperation.insert(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
