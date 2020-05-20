package routes.subroutes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import dbmanagment.operation.StraordinarioOperation
import routes.exceptions
import utils.caseclass.CaseClassDB.Straordinario
import utils.jsonformat.JsonFormats._

import scala.util.Success
object StraordinarioRoute  {
  def getStraordinario(id: Int): Route =
    get {
      onComplete(StraordinarioOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllStraordinario: Route =
    post {
      onComplete(StraordinarioOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createStraordinario(): Route =
    post {
      entity(as[Straordinario]) { order =>
        onComplete(StraordinarioOperation.insert(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
