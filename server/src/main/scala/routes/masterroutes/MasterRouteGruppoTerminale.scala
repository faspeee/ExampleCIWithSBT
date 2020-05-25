package routes.masterroutes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import routes.subroutes.GruppoTerminaleRoute._


object MasterRouteGruppoTerminale {
  val routeGruppoTerminale: Route = concat(
    path("getgruppoterminale" / IntNumber) {
      id => getGruppoTerminale(id)
    })
}
