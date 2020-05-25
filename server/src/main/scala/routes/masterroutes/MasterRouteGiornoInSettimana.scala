package routes.masterroutes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import routes.subroutes.GiornoInSettimanaRoute._


object MasterRouteGiornoInSettimana {
  val routeGiornoInSettimana: Route = concat(
    path("getgiornoinsettimana" / IntNumber) {
      id => getGiornoInSettimana(id)
    })
}
