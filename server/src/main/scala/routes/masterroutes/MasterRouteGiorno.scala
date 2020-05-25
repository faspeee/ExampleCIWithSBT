package routes.masterroutes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import routes.subroutes.GiornoRoute._

object MasterRouteGiorno {
  val routeGiorno: Route = concat(
    path("getgiorno" / IntNumber) {
      id => getGiorno(id)
    })
}
