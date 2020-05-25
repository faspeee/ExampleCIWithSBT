package view.scenes
import java.net.URL
import java.util.ResourceBundle

import controller.ZonaController
import utils.caseclass.CaseClassDB.Zona
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.application.Platform
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
      val center = ZonaBox()
      center.loading()
      myController.setView(this)
      pane.setCenter(center.pane())
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
        pane.setCenter(center.pane())
        center.setZone(zones)
      })
    }
  }

  def apply(primaryStage: Stage, oldScene:Option[Scene]) = new ZonaViewImpl()(primaryStage,oldScene)
}
