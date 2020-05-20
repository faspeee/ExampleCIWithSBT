package routes.subroutes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import dbmanagment.operation.SettimanaOperation
import routes.exceptions
import utils.caseclass.CaseClassDB.Settimana
import utils.jsonformat.JsonFormats._

import scala.util.Success
object SettimanaRoute  {
  def getSettimana(id: Int): Route =
    get {
      onComplete(SettimanaOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllSettimana: Route =
    post {
      onComplete(SettimanaOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createSettimana(): Route =
    post {
      entity(as[Settimana]) { order =>
        onComplete(SettimanaOperation.insert(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
