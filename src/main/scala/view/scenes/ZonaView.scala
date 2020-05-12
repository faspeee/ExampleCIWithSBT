package view.scenes
import java.net.URL
import java.util.ResourceBundle

import controller.ZonaController
import dbmanagment.CaseClassDB.Zona
import javafx.stage.Stage
import scalafx.application.JFXApp.PrimaryStage
import view.components.ZonaBox

trait ZonaView{
  def setZones(zone:List[Zona])
}
trait ZonaBoxObserver{
  def remove(ids:Set[Int])
  def insert(nome:String)
}

object ZonaView {
  private class ZonaViewImpl extends AbstractActiveScene with ZonaView with ZonaBoxObserver {
    private val myController = ZonaController()
    private val center = ZonaBox()
    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      myController.setView(this)
      center.setObserver(this)
      pane.setCenter(center.pane())
      myController.loadZones()
    }

    override def remove(ids: Set[Int]): Unit =
      myController.removeZones(ids)

    override def insert(nome: String): Unit =
      myController.insertZone(nome)

    override def setZones(zones:List[Zona]): Unit =
      center.setZone(zones)

  }

  def apply(primaryStage: Stage, oldStage:Option[Stage]) = new ZonaViewImpl()(primaryStage,oldStage)
}
