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
      entity(as[Straordinario]) { straordinario =>
        onComplete(StraordinarioOperation.insert(straordinario)) {
          case Success(t) =>  complete(StatusCodes.Created,Straordinario(
                                                        straordinario.data,
                                                        straordinario.personaId,
                                                        straordinario.turnoId,
                                                        Some(t)))
        }
      }
    }
}
