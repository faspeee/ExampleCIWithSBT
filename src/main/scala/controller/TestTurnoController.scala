package controller

import dbmanagment.TurnoTable
import mock.MockTurno
import view.scenes.{TestTurnoView}

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
    private val model:MockTurno = MockTurno()
    override def insertTurno(nomeTurno: String, fasciaOraria: String): Unit = {
     /* val id = model.insertTurno(nomeTurno,fasciaOraria)
      id.onComplete{
        case Success(value) => println("WEWE OK!  ",value)
        case Failure(exception) => println(exception)
      }*/
      println(model insertTurno(nomeTurno,fasciaOraria))
    }
  }
}
