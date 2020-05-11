package view.scenes

import java.net.URL
import java.util.ResourceBundle

import controller.RisorseUmaneController
import javafx.stage.Stage
import mock.Conducente
import view.components.{AssumiBox, LicenziaBox, RisorseUmaneLeft}

trait RisorseUmaneView {
  def refreshLicenzia()
}

trait LicenziaBoxObserver{
  def licenzia(ids:Set[String])
}

trait RisorseUmaneLeftObserver{
  def loadAssumi()
  def loadLicenzia()
}

trait AssumiBoxObserver{
  def assumi(assumi:Conducente)
}

object RisorseUmaneView{

  private class RisorseUmaneViewImpl extends AbstractActiveScene with RisorseUmaneView with RisorseUmaneLeftObserver with AssumiBoxObserver with LicenziaBoxObserver {
    private val myController = RisorseUmaneController()

    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      myController.setView(this)
      val left = RisorseUmaneLeft()
      left.setObserver(this)
      pane.setLeft(left.pane())
    }

    override def loadAssumi(): Unit ={
      val assumi = AssumiBox()
      assumi.setObserver(this)
      pane.setCenter(assumi.pane)
    }
    override def loadLicenzia(): Unit = {
      val licenzia = LicenziaBox()
      licenzia.setObserver(this)
      licenzia.setLista(myController.listaConducenti)
      pane.setCenter(licenzia.pane)
    }

    override def assumi(assunzione:Conducente): Unit =
      myController.assumi(assunzione)

    override def licenzia(ids: Set[String]): Unit =
      myController.licenzia(ids)

    override def refreshLicenzia(): Unit =
      loadLicenzia()
  }

  def apply(primaryStage:Stage, oldStage:Option[Stage]) = new RisorseUmaneViewImpl()(primaryStage,oldStage)
}