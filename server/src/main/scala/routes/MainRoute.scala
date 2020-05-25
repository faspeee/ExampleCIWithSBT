package routes

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.concat
import routes.masterroutes.MasterRouteContratto.routeContratto
import routes.masterroutes.MasterRouteGiornoInSettimana.routeGiornoInSettimana
import routes.masterroutes.MasterRouteGiorno.routeGiorno
import routes.masterroutes.MasterRouteGruppoTerminale.routeGruppoTerminale
import routes.masterroutes.MasterRouteParametro.routeParametro
import routes.masterroutes.MasterRoutePersona.routePersona
import routes.masterroutes.MasterRoutePresenza.routePresenza
import routes.masterroutes.MasterRouteRichiesta.routeRichiesta
import routes.masterroutes.MasterRouteRichiestaTeorica.routeRichiestaTeorica
import routes.masterroutes.MasterRouteRisultato.routeRisultato
import routes.masterroutes.MasterRouteSettimana.routeSettimana
import routes.masterroutes.MasterRouteStoricoContratto.routeStoricoContratto
import routes.masterroutes.MasterRouteStraordinario.routeStraordinario
import routes.masterroutes.MasterRouteTerminale.routeTerminale
import routes.masterroutes.MasterRouteTurno.routeTurno
import routes.masterroutes.MasterRouteZona.routeZona


object MainRoute {
  val route: Route =concat(
    routeContratto,
    routeGiornoInSettimana,
    routeGiorno,
    routeGruppoTerminale,
    routeParametro,
    routePersona,
    routePresenza,
    routeRichiesta,
    routeRichiestaTeorica,
    routeRisultato,
    routeSettimana,
    routeStoricoContratto,
    routeStraordinario,
    routeTerminale,
    routeTurno,
    routeZona
  )
}
