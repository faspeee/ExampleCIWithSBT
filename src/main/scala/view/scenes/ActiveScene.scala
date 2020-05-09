package view.scenes

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import view.loaders.SceneLoader

abstract class ActiveScene[A] extends Initializable{
  @FXML
  protected var pane: BorderPane = _
  protected var stage:Stage = _
  protected var myController:A = _

  def apply(primaryStage: Stage, controller: A){
    stage = primaryStage
    myController = controller
    SceneLoader.loadScene(primaryStage,this, "/fxml/BaseView.fxml")
  }
  def initialize(location: URL, resources: ResourceBundle): Unit = {}
}