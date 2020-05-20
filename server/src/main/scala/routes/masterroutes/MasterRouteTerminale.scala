package routes.masterroutes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import routes.subroutes.TerminaleRoute._
object MasterRouteTerminale {
  val routeTerminale: Route =
    concat(
      path("getterminale" / IntNumber) {
        id => getTerminale(id)
      },
      path("getallterminale") {
        getAllTerminale
      },
      path("createterminale" ) {
        createTerminale()
      },
      path("createallterminale") {
        createAllTerminale()
      },
      path("deleteterminale") {
        deleteTerminale()
      },
      path("deleteallterminale") {
        deleteTerminale()
      },
      path("updateterminale") {
        updateTerminale()
      }
    )
}
