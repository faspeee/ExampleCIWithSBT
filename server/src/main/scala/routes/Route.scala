package routes

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.concat
import routes.masterroutes.MasterRouteZona.routeZona
import routes.masterroutes.MasterRoutePersona.routePersona
import routes.masterroutes.MasterRouteTurno.routeTurno
import routes.masterroutes.MasterRouteTerminale.routeTerminale
object Route {
  val route: Route =concat(routePersona,routeTurno,routeZona,routeTerminale)
}
