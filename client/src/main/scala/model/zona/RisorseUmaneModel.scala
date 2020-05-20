package model.zona

import java.sql.Date

import akka.http.scaladsl.unmarshalling.Unmarshal
import model.GenericModel
import utils.caseclass.CaseClassDB.Persona

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.util.Success

trait RisorseUmaneModel extends GenericModel{
  def assumi(persona:Persona):Future[Unit]
  def licenzia(ids:Set[Int]): Future[Unit]
  def getAllPersone(): Future[List[Persona]]
}

object RisorseUmaneModel {
  private val instance = new RisorseUmaneModelImpl()

  def apply(): RisorseUmaneModel = instance

  private class RisorseUmaneModelImpl extends RisorseUmaneModel {
    import utils.jsonformat.JsonFormats._
    import akka.http.scaladsl.client.RequestBuilding.Post

    override def assumi(persona: Persona): Future[Unit] = {
      val pers = Promise[Unit]
      val req2 = Post("http://localhost:8080/createpersona",persona)
      dispatcher.serverRequest(req2).onComplete(_=> pers.success())
      pers.future
    }

    override def licenzia(ids: Set[Int]): Future[Unit] = {
      val pers = Promise[Unit]
      var list: List[Persona] = List()
      ids.foreach(x => list = Persona("","",new Date(1),"",1,None,Some(x))::list)
      val req2 = Post("http://localhost:8080/deleteallpersona",list)
      dispatcher.serverRequest(req2).andThen(_=>pers.success())
      pers.future
    }


    override def getAllPersone(): Future[List[Persona]] = {
      val list = Promise[List[Persona]]
      val req2 = Post("http://localhost:8080/getallpersona")
      dispatcher.serverRequest(req2).onComplete{
        case Success(result) =>
          Unmarshal(result).to[List[Persona]].onComplete(t => list.success(t.get))
      }
      list.future
    }
  }

}
