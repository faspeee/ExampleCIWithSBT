package routes.masterroutes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import routes.subroutes.PersonaRoute._
object MasterRoutePersona {
  val routePersona: Route =
    concat(
      path("getpersona" / IntNumber) {
        id => getPersona(id)
      },
      path("getallpersona") {
        getAllPersona
      },
      path("createpersona" ) {
        createPersona()
      },
      path("createallpersona") {
        createAllPersona()
      },
      path("deletepersona") {
        deletePersona()
      },
      path("deleteallpersona") {
        deleteAllPersona()
      },
      path("updatepersona") {
        updatePersona()
      },
      path("loginpersona") {
        loginPersona()
      }

    )
}
