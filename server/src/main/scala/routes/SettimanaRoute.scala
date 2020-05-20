package routes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import akka.http.scaladsl.server.Route
import dbmanagment.operation.SettimanaOperation
import utils.caseclass.CaseClassDB.Settimana

import scala.util.Success
import utils.jsonformat.JsonFormats._
object SettimanaRoute  {
  def getZona(id: Int): Route =
    get {
      onComplete(SettimanaOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllZona: Route =
    get {
      onComplete(SettimanaOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createZona(): Route =
    post {
      entity(as[Settimana]) { order =>
        onComplete(SettimanaOperation.insert(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}
