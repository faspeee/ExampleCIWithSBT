package view.scenes

import java.net.URL
import java.util.ResourceBundle

import controller.TestTurnoController
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.stage.Stage
import view.components.TopHBox

trait TestTurnoView

trait TopHBoxObserver{
  def insertTurno(nomeTurno:String, fasciaOraria:String)
}

/**
 * Mi sono rotto, funziona uguale a [[view.scenes.LoginView]]
 */
object TestTurnoView {
  private class TestTurnoViewImpl extends AbstractActiveScene with TestTurnoView with TopHBoxObserver {
    private val myController = TestTurnoController()

    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      myController.setView(this)
      val top = TopHBox()
      top.setObserver(this)
      pane.setTop(top.pane())
    }
    override def insertTurno(nomeTurno: String, fasciaOraria: String): Unit = {
      myController.insertTurno(nomeTurno, fasciaOraria)
      goBack
    }
  }

  def apply(primaryStage:Stage,oldStage:Option[Scene]) = new TestTurnoViewImpl()(primaryStage,oldStage)
}
