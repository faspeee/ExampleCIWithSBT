package model.zona

import model.GenericModel
import utils.caseclass.CaseClassDB.Persona
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.{Future, Promise}

trait RisorseUmaneModel extends GenericModel{
  def assumi(persona:Persona):Future[Unit]
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
  }

}
