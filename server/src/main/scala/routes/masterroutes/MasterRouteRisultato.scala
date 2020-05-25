package routes.masterroutes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import routes.subroutes.RisultatoRoute._


object MasterRouteRisultato {
  val routeRisultato: Route = concat(
    path("getrisultato" / IntNumber) {
      id => getRisultato(id)
    })
}
