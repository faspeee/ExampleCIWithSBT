package view

import java.net.URL
import java.util.ResourceBundle

import controller.FraController
import javafx.application.Application
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, TextArea, TextField}
import javafx.stage.Stage
import model.Model
import utils.observerPattern.Observer

import scala.util.{Failure, Success}

trait View extends Initializable with Observer [Model] {

}

class FraMainView extends Application {
  var view: FraView = _

  def d = Application.launch()

  override def start(primaryStage: Stage): Unit = view = new FraView(primaryStage)
}

class FraView extends View{
  @FXML var text: TextArea = _
  @FXML var readB: Button = _
  var stage : Stage = _

  def this(primaryStage : Stage){
    this()
    this.stage=primaryStage
    val lo:FXMLLoader = new FXMLLoader(getClass.getResource("/fraView/prima.fxml"))
    FraController.setObserver(this)
    lo.setController(this)
    // Load the FXML, the controller will be instantiated
    lo.load()
    primaryStage.setScene(new Scene(lo.getRoot[Parent]))
    primaryStage.show
  }

  def print(message: String) = text.setText(message + text.getText)

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    readB.setOnAction(_ => {
      text.setText(":P")
      FraController.aggiungi(2)
    })
  }

  override def notification(subject: Model): Unit = print(subject.get().toString)
}