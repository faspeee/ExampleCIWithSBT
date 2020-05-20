package routes.subroutes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import dbmanagment.operation.ZonaOperation
import routes.exceptions
import utils.caseclass.CaseClassDB.Zona
import utils.jsonformat.JsonFormats._

import scala.util.Success
object ZonaRoute {
  def getZona(id: Int): Route =
    get {
     onComplete(ZonaOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllZona: Route =
    post {
      onComplete(ZonaOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
        case Success(Nil) => complete(StatusCodes.NotFound)
      }
    }
  def createZona(): Route =
    post {
      entity(as[Zona]) { zona =>
        onComplete(ZonaOperation.insert(zona)) {
          case Success(t) =>  complete(StatusCodes.Created,Zona(zona.zones,Some(t)))
        }
      }
    }
  def createAllZona(): Route =
    post {
      entity(as[List[Zona]]) { zona =>
        onComplete(ZonaOperation.insertAll(zona)) {
          case Success(t) =>  complete(StatusCodes.Created)
        }
      }
    }
  def deleteZona(): Route =
    post {
      entity(as[Zona]) { zona =>
        onComplete(ZonaOperation.delete(zona)) {
          case Success(t)  =>  complete(StatusCodes.OK)
        }
      }
    }
  def deleteAllZona(): Route =
    post {
      entity(as[List[Zona]]) { zona =>
        onComplete(ZonaOperation.deleteAll(zona)) {
          case Success(t) =>  complete(StatusCodes.OK)
        }
      }
    }
  def updateZona(): Route =
    post {
      entity(as[Zona]) { zona =>
        onComplete(ZonaOperation.update(zona)) {
          case Success(t) =>  complete(StatusCodes.Created)
        }
      }
    }
}
