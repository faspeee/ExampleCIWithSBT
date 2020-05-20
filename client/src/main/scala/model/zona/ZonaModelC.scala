package model.zona

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.{HttpMethods, HttpRequest}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.SystemMaterializer
import utils.caseclass.CaseClassDB.Zona

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.util.Success

trait ZonaModelC{
  def zone: Future[List[Zona]]
}


object ZonaModelC{
  private val instance = new ZonaModelHttp()

  def apply(): ZonaModelC = instance

  private class ZonaModelHttp extends ZonaModelC {
    implicit val system = ActorSystem("ZonaClient")
    implicit val materializer = SystemMaterializer(system)
    implicit val ex = system.dispatchers

    import utils.jsonformat.JsonFormats._

    override def zone: Future[List[Zona]] = {
      val zonaAll = Promise[List[Zona]]
      val uriZ = "http://localhost:8080/getallzona"
      val zonaRequest = HttpRequest(
        uri = uriZ,
        method = HttpMethods.GET
      )
      Http().singleRequest(zonaRequest).onComplete {
        case Success(result) =>
          Unmarshal(result).to[List[Zona]].onComplete(t => zonaAll.success(t.get))
      }
      zonaAll.future
    }
  }
}
