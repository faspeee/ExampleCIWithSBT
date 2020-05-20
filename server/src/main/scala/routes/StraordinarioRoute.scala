package routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import dbmanagment.operation.StraordinarioOperation
import utils.caseclass.CaseClassDB.Straordinario
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, post, _}
import scala.util.Success
import utils.jsonformat.JsonFormats._

object StraordinarioRoute  {
  def getStraordinario(id: Int): Route =
    get {
      onComplete(StraordinarioOperation.select(id)) {
        case Success(t) =>    complete((StatusCodes.Found,t))
        case Success(None) => complete(StatusCodes.NotFound)
      }
    }
  def getAllStraordinario: Route =
    get {
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
