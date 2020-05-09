package view.loaders

import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage
import view.scenes.ActiveScene

import scala.language.postfixOps

object SceneLoader {
  def loadScene[A](primaryStage: Stage, controller: ActiveScene[A], layoutPath: String): Unit = {
    val loader = new FXMLLoader(getClass.getResource(layoutPath))
    loader.setController(controller)
    loader.load
    primaryStage.setScene(new Scene(loader.getRoot[Parent]))
    primaryStage show
  }
}
