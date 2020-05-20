package routes.masterroutes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import routes.subroutes.TurnoRoute._
import routes.subroutes.ZonaRoute._

object MasterRouteZona {
  val routeZona: Route =
    concat(
      path("getzona" / IntNumber) {
        id => getZona(id)
      },
      path("getallzona") {
         getAllZona
      },
      path("createzona" ) {
          createZona()
      },
      path("createallzona") {
        createAllZona()
      },
      path("deletezona") {
         deleteZona()
      },
      path("deleteallzona") {
        deleteAllZona()
      },
      path("updatezona") {
        updateZona()
      }
    )
}
