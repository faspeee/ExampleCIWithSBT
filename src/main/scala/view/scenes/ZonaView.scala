package view.scenes
import java.net.URL
import java.util.ResourceBundle

import controller.ZonaController
import dbmanagment.CaseClassDB.Zona
import javafx.stage.Stage
import scalafx.application.JFXApp.PrimaryStage
import scalafx.application.Platform
import view.components.ZonaBox

trait ZonaView{
  def setZones(zone:List[Zona])
}
trait ZonaBoxObserver{
  def remove(ids:Set[Int])
  def insert(nome:String)
}

object ZonaView {
  private class ZonaViewImpl extends AbstractActiveSceneWithTop with ZonaView with ZonaBoxObserver {
    private val myController = ZonaController()
    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      myController.setView(this)
      myController.loadZones()
    }

    override def remove(ids: Set[Int]): Unit =
      myController.removeZones(ids)

    override def insert(nome: String): Unit =
      myController.insertZone(nome)

    override def setZones(zones:List[Zona]): Unit = {
      Platform.runLater(()=>{
        val center = ZonaBox()
        center.setObserver(this)
        center.setZone(zones)
        pane.setCenter(center.pane())
      })
    }

  }

  def apply(primaryStage: Stage, oldStage:Option[Stage]) = new ZonaViewImpl()(primaryStage,oldStage)
}
