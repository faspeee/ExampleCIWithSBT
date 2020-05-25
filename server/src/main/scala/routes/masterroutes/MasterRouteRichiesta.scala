package routes.masterroutes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import routes.subroutes.RichiestaRoute._


object MasterRouteRichiesta {
  val routeRichiesta: Route = concat(
    path("getrichiesta" / IntNumber) {
      id => getRichiesta(id)
    })
}
