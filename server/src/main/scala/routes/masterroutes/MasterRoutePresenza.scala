package routes.masterroutes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import routes.subroutes.PresenzaRoute._


object MasterRoutePresenza {
  val routePresenza: Route = concat(
    path("getpresenzaRoute" / IntNumber) {
      id => getPresenza(id)
    })
}
