package routes.masterroutes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import routes.subroutes.ParametroRoute._


object MasterRouteParametro {
  val routeParametro: Route = concat(
    path("getparametro" / IntNumber) {
      id => getParametro(id)
    })
}
