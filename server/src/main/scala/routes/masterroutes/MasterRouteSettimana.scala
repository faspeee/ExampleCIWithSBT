package routes.masterroutes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import routes.subroutes.SettimanaRoute._

object MasterRouteSettimana {
  val routeSettimana: Route = concat(
    path("getsettimana" / IntNumber) {
      id => getSettimana(id)
    })
}
