package view.scenes

import java.net.URL
import java.util.ResourceBundle

import controller.RisorseUmaneController
import utils.caseclass.CaseClassDB.{Persona, Terminale}
import javafx.application.Platform
import javafx.scene.Scene
import javafx.stage.Stage
import view.components.{AssumiBox, LicenziaBox, RisorseUmaneLeft}

trait RisorseUmaneView {
  def assumiToLoad(terminali:List[Terminale])
  def licenziaToLoad(persone:List[Persona])
}

trait LicenziaBoxObserver{
  def licenzia(ids:Set[Int])
}

trait RisorseUmaneLeftObserver{
  def loadAssumi()
  def loadLicenzia()
  def loadTestTurno()
}

trait AssumiBoxObserver{
  def assumi(assumi:Persona)
}

object  RisorseUmaneView{

  private class RisorseUmaneViewImpl extends AbstractActiveSceneWithTop with RisorseUmaneView with RisorseUmaneLeftObserver with AssumiBoxObserver with LicenziaBoxObserver {
    private val myController = RisorseUmaneController()

    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      myController.setView(this)
      val left = RisorseUmaneLeft()
      left.setObserver(this)
      pane.setLeft(left.pane())
    }

    override def loadAssumi(): Unit ={
      myController.loadAssumi()
    }
    override def loadLicenzia(): Unit = {
      myController.loadLicenzia()
    }

    override def assumi(persona:Persona): Unit =
      myController.assumi(persona)

    override def licenzia(ids: Set[Int]): Unit =
      myController.licenzia(ids)

    override def licenziaToLoad(persone: List[Persona]): Unit = {
      Platform.runLater(()=>{
        val licenzia = LicenziaBox()
        licenzia.setObserver(this)
        licenzia.setLista(persone)
        pane.setCenter(licenzia.pane)
      })
    }

    override def assumiToLoad(terminali: List[Terminale]): Unit = {
      Platform.runLater(()=>{
        val assumi = AssumiBox()
        assumi.setObserver(this)
        assumi.setTerminali(terminali)
        pane.setCenter(assumi.pane)
      })
    }

    override def loadTestTurno(): Unit =
      TestTurnoView(stage,Option(stage.getScene))
  }

  def apply(primaryStage:Stage, oldScene:Option[Scene]) = new RisorseUmaneViewImpl()(primaryStage,oldScene)
}