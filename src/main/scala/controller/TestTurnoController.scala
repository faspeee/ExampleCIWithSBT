package controller

import dbmanagment.TurnoTable
import view.scenes.TestTurnoObservable

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Controller della schermata di turni. Estende [[controller.Controller]] con tipo [[view.scenes.TestTurnoObservable]]
 * per dire che è un controller di una view che è LoginObservable
 */
trait TestTurnoController extends Controller[TestTurnoObservable]{
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
  def apply(): TestTurnoController = new TestTurnoControllerImpl()

  private class TestTurnoControllerImpl() extends AbstractController[TestTurnoObservable] with TestTurnoController{
    private val model:TurnoTable = TurnoTable()

    override def insertTurno(nomeTurno: String, fasciaOraria: String): Unit = {
      val id = model.insertTurno(nomeTurno,fasciaOraria)
      id.onComplete{
        case Success(value) => println("WEWE OK!  ",value)
        case Failure(exception) => println(exception)
      }
    }
  }
}
