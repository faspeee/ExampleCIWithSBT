package routes.subroutes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, onComplete, post}
import akka.http.scaladsl.server.Route
import dbmanagment.operation.ContrattoOperation
import utils.caseclass.CaseClassDB.Contratto
import utils.jsonformat.JsonFormats._

import scala.util.{Failure, Success}
import routes.exceptions.Exceptions.myExceptionHandler

object ContrattoRoute{
  def getContratto(id: Int): Route =
    get {
      onComplete(ContrattoOperation.select(id)){
         case Success(t) =>    complete((StatusCodes.Found,t))
         case Failure(_) => complete(StatusCodes.NotFound)
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
      entity(as[Contratto]) { contratto =>
        onComplete(ContrattoOperation.insert(contratto)) {
          case Success(t) =>  complete(StatusCodes.Created)
        }
      }
    }
}
