package routes.subroutes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, onComplete, post}
import akka.http.scaladsl.server.Route
import dbmanagment.operation.GiornoInSettimanaOperation
import utils.caseclass.CaseClassDB.GiornoInSettimana

import utils.jsonformat.JsonFormats._
import scala.util.Success

object GiornoInSettimanaRoute {
  def getGiornoInSettimana(id: Int): Route =
    get {
      onComplete(GiornoInSettimanaOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllGiornoInSettimana: Route =
    post {
      onComplete(GiornoInSettimanaOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createGiornoInSettimana(): Route =
    post {
      entity(as[GiornoInSettimana]) { giornoInSettimana =>
        onComplete(GiornoInSettimanaOperation.insert(giornoInSettimana)) {
          case Success(t)  =>  complete(StatusCodes.Created)
        }
      }
    }
}
