package controller

import dbmanagment.CaseClassDB.Turno
import dbmanagment.TurnoTable
import mock.MockTurno
import view.scenes.TestTurnoView

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Controller della schermata di turni. Estende [[controller.Controller]] con tipo [[view.scenes.TestTurnoView]]
 * per dire che è un controller di una view che è LoginObservable
 */
trait TestTurnoController extends Controller[TestTurnoView]{
  /**
   * Inserimento del turno in database.
   * @param nomeTurno
   *                  Nome da inserire
   * @param fasciaOraria
   *                     Fascia da inserire
   */
  def insertTurno(nomeTurno:String, fasciaOraria:String)
}

/**
 * Companion del trait [[controller.TestTurnoController]]
 */
object TestTurnoController {
  private val instance = new TestTurnoControllerImpl()
  def apply(): TestTurnoController = instance

  private class TestTurnoControllerImpl() extends AbstractController[TestTurnoView] with TestTurnoController{
    override def insertTurno(nomeTurno: String, fasciaOraria: String): Unit = {
      val turno = Turno(nomeTurno,fasciaOraria)
    }
  }
}
