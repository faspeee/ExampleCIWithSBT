package view

import java.net.URL
import java.util.ResourceBundle

import controller.MainController
import view.components.{Component, TopHBoxView}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.language.postfixOps

trait MainView{
  def loadComponent()
}

trait TopObserver{
  def turnoAdded(nomeTurno:String, fasciaOraria:String)
}

object MainView{
  private class MainViewImpl extends Initializable with MainView with TopObserver{
    @FXML
    private var insert:Button = _
    @FXML
    private var pane: BorderPane = _
    private var stage:Stage = _
    private var myController:MainController = _

    def apply(primaryStage: Stage, controller: MainController){
      stage = primaryStage
      myController = controller
      controller.setView(this)
      val loader = new FXMLLoader(getClass.getResource("/fxml/MainView.fxml"))
      loader.setController(this)
      loader.load
      stage.setScene(new Scene(loader.getRoot[Parent]))
      stage show
    }
    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      insert.setOnAction(e =>{
        myController.loadOnView()
      })
    }

    override def loadComponent(): Unit = {
      val top = TopHBoxView()
      top.setObserver(this)
      pane.setTop(top.pane())
    }

    override def turnoAdded(nomeTurno: String, fasciaOraria: String): Unit =
      myController.turnoAdded(nomeTurno,fasciaOraria)
  }
  def apply(primaryStage:Stage, controller: MainController) = new MainViewImpl()(primaryStage,controller)
}
