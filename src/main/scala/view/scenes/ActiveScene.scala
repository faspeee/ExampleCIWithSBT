package view.scenes

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import view.loaders.SceneLoader

/**
 * Rappresentazione della scena attiva su schermo
 */
trait ActiveScene{
  /**
   * Permette di tornare allo [[javafx.stage.Stage]] precedente, se presente.
   */
  def goBack()
}

/**
 * Template class per rappresentare scene attive, implementa [[view.scenes.ActiveScene]]
 */
abstract class AbstractActiveScene extends Initializable with ActiveScene {
  /**
   * Pannello di base per ogni scena, caricato dall'XML
   */
  @FXML
  protected var pane: BorderPane = _
  /**
   * Stage precedente, Opzionale
   */
  protected var previousStage:Option[Stage] = _
  /**
   * Stage attuale
   */
  protected var stage:Stage = _

  def apply(primaryStage: Stage, oldStage:Option[Stage] = None){
    stage = primaryStage
    previousStage = oldStage
    SceneLoader.loadScene(primaryStage,this, "/fxml/BaseView.fxml")
  }

  override def goBack(): Unit = {
    stage.hide
    previousStage.get.show
  }

}