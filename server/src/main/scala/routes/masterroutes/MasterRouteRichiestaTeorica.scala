package routes.masterroutes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import routes.subroutes.RichiestaTeoricaRoute._


object MasterRouteRichiestaTeorica {
  val routeRichiestaTeorica: Route = concat(
    path("getrichiestateorica" / IntNumber) {
      id => getRichiestaTeorica(id)
    })
}
