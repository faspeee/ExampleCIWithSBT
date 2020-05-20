package routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import akka.http.scaladsl.server.Route
import dbmanagment.operation.ZonaOperation
import utils.caseclass.CaseClassDB.Zona
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import utils.jsonformat.JsonFormats._
object ZonaRoute {
  def getZona(id: Int): Route =
    get {
     onSuccess(ZonaOperation.select(id)) {
        case Some(t) =>   complete((StatusCodes.Found,t))
        case None  =>  complete(StatusCodes.NotFound)
      }
    }
  def getAllZona: Route =
    get {
      onSuccess(ZonaOperation.selectAll) {
        case h::t =>   complete((StatusCodes.Found,ZonaAll(h::t)))
        case _  =>  complete(StatusCodes.NotFound)
      }
    }
  def createZona(): Route =
    post {
      entity(as[Zona]) { order =>
        onSuccess(ZonaOperation.insert(order)) {
          case _:Int =>   complete(StatusCodes.Created)
          case _  =>  complete(StatusCodes.BadRequest)
        }
      }
    }
}
