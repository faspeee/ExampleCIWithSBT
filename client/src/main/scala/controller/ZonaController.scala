package controller

import utils.caseclass.CaseClassDB.Zona
import dbmanagment.operation.ImplicitCrudG._
import dbmanagment.operation.ZonaOperation

import scala.concurrent.duration.Duration
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

    override def insertZone(nome: String): Unit = {
    ZonaOperation.insert(Zona(nome)) onComplete  (_ => loadZones())
    }

    override def removeZones(ids: Set[Int]): Unit ={
      var list: List[Zona] = List()
      ids.foreach(x => list = Zona("",Some(x))::list)
      ZonaOperation.deleteAll(list) onComplete  (_ => loadZones())
    }

    override def loadZones(): Unit = {
      ZonaOperation.selectAll.onComplete{
       case Success(x) => myView.setZones(x)
       case _ => println("error")
     }
    }
  }

}