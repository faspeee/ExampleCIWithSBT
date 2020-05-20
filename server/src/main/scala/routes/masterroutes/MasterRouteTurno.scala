package routes.masterroutes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import routes.subroutes.TurnoRoute._

object MasterRouteTurno {
  val routeTurno: Route =
    concat(
      path("getturno" / IntNumber) {
        id => getTurno(id)
      },
      path("getallturno") {
        getAllTurno
      },
      path("createturno" ) {
        createTurno()
      },
      path("createallturno") {
        createAllTurno()
      },
      path("deleteturno") {
        deleteTurno()
      },
      path("deleteallturno") {
        deleteAllTurno()
      },
      path("updateturno") {
        updateTurno()
      }
    )
}
