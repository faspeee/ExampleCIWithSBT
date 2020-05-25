package routes.subroutes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import dbmanagment.operation.RichiestaOperation
import routes.exceptions
import utils.caseclass.CaseClassDB.Richiesta
import utils.jsonformat.JsonFormats._

import scala.util.Success
object RichiestaRoute {
  def getRichiesta(id: Int): Route =
    get {
      onComplete(RichiestaOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllRichiesta: Route =
    post {
      onComplete(RichiestaOperation.selectAll) {
        case Success(t) =>  complete((StatusCodes.Found,t))
      }
    }
  def createRichiesta(): Route =
    post {
      entity(as[Richiesta]) { richiesta =>
        onComplete(RichiestaOperation.insert(richiesta)) {
          case Success(t)=>  complete(StatusCodes.Created)
        }
      }
    }
}
