package view.loaders

import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage
import view.scenes.ActiveScene

import scala.language.postfixOps

/**
 * Simile a [[view.loaders.ComponentLoader]].
 */
object SceneLoader {
  /**
   * Simile al metodo di [[view.loaders.SceneLoader]] ma mostra lo stage caricato invece di tornarlo
   * @param primaryStage
   * @param controller
   * @param layoutPath
   */
  def loadScene(primaryStage: Stage, controller: ActiveScene, layoutPath: String): Unit = {
    val loader = new FXMLLoader(getClass.getResource(layoutPath))
    loader.setController(controller)
    loader.load
    primaryStage.setScene(new Scene(loader.getRoot[Parent]))
    primaryStage show
  }
}
