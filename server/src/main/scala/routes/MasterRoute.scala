package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import routes.TurnoRoute._
import routes.ZonaRoute._

object MasterRoute {
  val route: Route =
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
      path("getturno" / IntNumber) {
        id => getTurno(id)
      },
      path("createturno") {
         createTurno()
      }
    )
}
