package model

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.SystemMaterializer

import scala.concurrent.Future

trait ModelDispatcher{
  def serverRequest(request:HttpRequest):Future[HttpResponse]
  implicit val system = ActorSystem("ZonaClient")
  implicit val materializer = SystemMaterializer(system)
  implicit val ex = system.dispatchers
}

object ModelDispatcher {
  private val istance: ModelDispatcher = new ModelDispatcherImpl

  def apply(): ModelDispatcher = istance

  private class ModelDispatcherImpl extends ModelDispatcher{
    override def serverRequest(request: HttpRequest): Future[HttpResponse] =
      Http().singleRequest(request)
  }
}
