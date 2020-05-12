package controller

import dbmanagment.CaseClassDB.Zona
import dbmanagment.GenericCrudClass
import view.scenes.ZonaView

trait ZonaController extends Controller[ZonaView]{
  def insertZone(nome:String)
  def removeZones(ids:Set[Int])
  def loadZones()
}

object ZonaController {
  private val instance = new ZonaControllerImpl()

  def apply(): ZonaController = instance

  private class ZonaControllerImpl() extends AbstractController[ZonaView] with ZonaController{

    override def insertZone(nome: String): Unit =
      GenericCrudClass().insert(Zona(nome))

    override def removeZones(ids: Set[Int]): Unit =
      println(ids)

    override def loadZones(): Unit =
      myView.setZones(List(Zona("mario",Some(1)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2)),Zona("mazro",Some(2))))
  }

}
