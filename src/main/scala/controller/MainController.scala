package controller

import java.net.URL
import java.util.ResourceBundle

import controller.components.{Component, TopHBoxController}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.language.postfixOps

object MainController{
  private class MainController extends Initializable{
    @FXML
    private var insert:Button = _
    @FXML
    private var pane: BorderPane = _

    private var stage:Stage = _
    def apply(primaryStage: Stage){
      stage = primaryStage
      val loader = new FXMLLoader(getClass.getResource("/fxml/MainView.fxml"))
      loader.setController(this)
      loader.load
      stage.setScene(new Scene(loader.getRoot[Parent]))
      println("aa")
      println(insert)
      stage show
    }

    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      val panel:Component = TopHBoxController()
      pane.setTop(panel.getComponent())
      insert.setOnAction(e => {
        println("RIRIIRIR")
      })
      println("mi")
    }
  }
  def apply(primaryStage:Stage) = new MainController()(primaryStage)
}