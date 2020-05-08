package view.components

import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.{Button, TextField}
import javafx.scene.layout.Pane

trait Component{
  def getComponent():Pane
}

object TopHBoxController {
  class TopHBoxController extends Initializable with Component {
    @FXML
    var insert:Button = _
    @FXML
    var nomeGiorno:TextField = _
    @FXML
    var fasciaOraria:TextField = _
    private var pane: Pane = _

    def apply(): Unit ={
      val loader = new FXMLLoader(getClass.getResource("/fxml/components/TopHBox.fxml"))
      loader.setController(this)
      pane = loader.load
      println(pane)
    }

    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      insert.setOnAction(action =>{
        println(nomeGiorno.getText, fasciaOraria.getText)
      })
    }
    override def getComponent(): Pane = pane
  }

  def apply() = {
    val a = new TopHBoxController()
    a()
    a
  }
}
