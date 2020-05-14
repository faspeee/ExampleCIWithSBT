package controller

import java.net.URL
import java.util.ResourceBundle

import javafx.application.Platform
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, TextField}
import javafx.stage.Stage

import scala.language.postfixOps
import setting.{InsertDataBase, SelectDataBase}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}
abstract class  HomeController extends Initializable{
  @FXML var insert: Button = _
  @FXML var select: Button = _
  @FXML var delete: Button = _
  @FXML var update: Button = _
  @FXML var text:TextField = _
  var stage:Stage = _
}
object HomeController extends HomeController{

  def apply(primaryStage : Stage){
    stage=primaryStage
    val home:FXMLLoader = new FXMLLoader(getClass.getResource("/fxml/home.fxml"))
    home.setController(this)
    home.load()
    stage.setScene(new Scene(home.getRoot[Parent]))
    stage.show()
  }
  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    insert.setOnAction(e => {
   
      InsertDataBase.insertTerminale()
 
    })
    select.setOnAction(e => {
      InsertDataBase.selectFilter()
    })
    update.setOnAction(e => {
      InsertDataBase.updateZona()
    })
    delete.setOnAction(e => {
      InsertDataBase.deleteZona()
    })
  }
}
