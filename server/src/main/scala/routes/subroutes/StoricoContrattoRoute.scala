package routes.subroutes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, onComplete, post}
import akka.http.scaladsl.server.Route
import dbmanagment.operation.StoricoContrattoOperation
import utils.caseclass.CaseClassDB.StoricoContratto
import utils.jsonformat.JsonFormats._
import scala.util.Success

object StoricoContrattoRoute  {
  def getStoricoContratto(id: Int): Route =
    get {
      onComplete(StoricoContrattoOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllStoricoContratto: Route =
    post {
      onComplete(StoricoContrattoOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createStoricoContratto(): Route =
    post {
      entity(as[StoricoContratto]) { order =>
        onComplete(StoricoContrattoOperation.insert(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
