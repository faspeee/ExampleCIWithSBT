package routes.masterroutes

import akka.http.scaladsl.server.Directives._
import routes.subroutes.StoricoContrattoRoute._

object MasterRouteStoricoContratto {
  val routeStoricoContratto = concat(
    path("getstoricocontratto" / IntNumber) {
      id => getStoricoContratto(id)
    })
}
