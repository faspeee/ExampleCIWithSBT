package model.zona

import akka.http.scaladsl.model.{HttpMethods, HttpRequest}
import akka.http.scaladsl.unmarshalling.Unmarshal
import model.{GenericModel, ModelDispatcher}
import utils.caseclass.CaseClassDB.Zona

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.util.Success

trait ZonaModelC extends GenericModel{
  def zone: Future[List[Zona]]
  def addZona(nome:String): Future[Unit]
  def deleteAllZona(ids: Set[Int]):Future[Unit]
}

object ZonaModelC{
  private val instance = new ZonaModelHttp()

  def apply(): ZonaModelC = instance

  private class ZonaModelHttp extends ZonaModelC {
    import utils.jsonformat.JsonFormats._
    import akka.http.scaladsl.client.RequestBuilding.Post

    override def zone: Future[List[Zona]] = {
      val zonaAll = Promise[List[Zona]]
      val uriZ = "http://localhost:8080/getallzona"
      val zonaRequest = HttpRequest(
        uri = uriZ,
        method = HttpMethods.POST
      )

      dispatcher.serverRequest(zonaRequest).onComplete {
        case Success(result) =>
          Unmarshal(result).to[List[Zona]].onComplete(t => zonaAll.success(t.get))
      }
      zonaAll.future
    }

    override def addZona(nome: String): Future[Unit] = {
      val zona = Promise[Unit]
      val zonaToIns = Zona(nome)
      val req2 = Post("http://localhost:8080/createzona",zonaToIns)
      dispatcher.serverRequest(req2).onComplete(_=> zona.success())
      zona.future
    }

    override def deleteAllZona(ids: Set[Int]): Future[Unit] = {
      val fut = Promise[Unit]
      var list: List[Zona] = List()
      ids.foreach(x => list = Zona("",Some(x))::list)
      val req2 = Post("http://localhost:8080/deleteallzona",list)
      dispatcher.serverRequest(req2).onComplete(_ => fut.success())
      fut.future
    }
  }
}
