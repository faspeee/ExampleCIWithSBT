package controller

import utils.caseclass.CaseClassDB.Zona
import dbmanagment.operation.ZonaOperation

import scala.concurrent.duration.Duration
import model.zona.ZonaModelC
import utils.observerPattern.Observer
import view.scenes.ZonaView

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success

trait ZonaController extends Controller[ZonaView]{
  def insertZone(nome:String)
  def removeZones(ids:Set[Int])
  def loadZones()
}

object ZonaController {
  private val instance = new ZonaControllerImpl()

  def apply(): ZonaController = instance

  private class ZonaControllerImpl() extends AbstractController[ZonaView] with ZonaController{

    val model = ZonaModelC()

    override def insertZone(nome: String): Unit = {
      model.addZona(nome).onComplete(_ => loadZones())
    }

    override def removeZones(ids: Set[Int]): Unit ={
      model.deleteAllZona(ids).onComplete(_=> loadZones())
    }

    override def loadZones(): Unit = {
      model.zone.onComplete{
        case Success(zone) => myView.setZones(zone)
      }
    }
  }

}