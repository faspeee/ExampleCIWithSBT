package routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import akka.http.scaladsl.server.Route
import dbmanagment.operation.ZonaOperation
import utils.jsonformat.JsonFormats._

import scala.util.Success
import utils.caseclass.CaseClassDB.Zona
object ZonaRoute {
  def getZona(id: Int): Route =
    get {
      onComplete(ZonaOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllZona: Route =
    get {
      onComplete(ZonaOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createZona(): Route =
    post {
      entity(as[Zona]) { order =>
        onComplete(ZonaOperation.insert(order)) {
          case Success(t) if t==1 =>  complete(StatusCodes.Created)
        }
      }
    }
}