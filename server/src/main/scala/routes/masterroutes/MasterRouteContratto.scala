package routes.masterroutes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import routes.subroutes.ContrattoRoute._

object MasterRouteContratto {
  val routeContratto: Route = concat(
    path("getcontratto" / IntNumber) {
      id => getContratto(id)
    })
}
