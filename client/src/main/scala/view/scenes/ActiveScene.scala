package view.scenes

import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, Initializable}
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import view.components.TopBar
import view.loaders.{ComponentLoader, SceneLoader}

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
  protected var previousScene:Option[Scene] = _
  /**
   * Stage attuale
   */
  protected var stage:Stage = _

  def apply(primaryStage: Stage, oldScene:Option[Scene] = None){
    stage = primaryStage
    previousScene = oldScene
    SceneLoader.loadScene(primaryStage,this, "/fxml/BaseView.fxml")
  }

  override def goBack(): Unit = {
    stage.setScene(previousScene.get)
  }
}

abstract class AbstractActiveSceneWithTop extends AbstractActiveScene {

  override def apply(primaryStage: Stage, oldScene: Option[Scene]): Unit = {
    super.apply(primaryStage, oldScene)
    val top = TopBar()
    top.setObserver(this)
    pane.setTop(top.pane())
  }

  def risorse() = {
    RisorseUmaneView(stage,Some(stage.getScene))
  }

  def zone(): Unit ={
    ZonaView(stage,Some(stage.getScene))
  }
}
