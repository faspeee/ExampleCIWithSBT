package routes.masterroutes

import akka.http.scaladsl.server.Route

import akka.http.scaladsl.server.Directives._
import routes.subroutes.StraordinarioRoute._
object MasterRouteStraordinario {
  val routeStraordinario: Route = concat(
    path("getstraordinario" / IntNumber) {
      id => getStraordinario(id)
    })
}
