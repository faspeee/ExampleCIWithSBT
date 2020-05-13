package view.components

import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.FXML
import javafx.scene.control.{Button, ComboBox, TextField}
import mock.Conducente
import view.scenes.AssumiBoxObserver

import scala.language.postfixOps

trait AssumiBox extends Component[AssumiBoxObserver]{

}

object AssumiBox{
  def apply(): AssumiBox = new AssumiBoxImpl()

  private class AssumiBoxImpl() extends AbstractComponent[AssumiBoxObserver]("/fxml/components/AssumiBox.fxml") with AssumiBox{
    @FXML
    var nome: TextField = _
    @FXML
    var cognome: TextField = _
    @FXML
    var contratto: ComboBox[String] = _
    @FXML
    var turno: ComboBox[String] = _
    @FXML
    var giorno1: ComboBox[String] = _
    @FXML
    var giorno2: ComboBox[String] = _
    @FXML
    var terminale: ComboBox[String] = _
    @FXML
    var numero: TextField = _
    @FXML
    var salva: Button = _

    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      salva.setOnAction(_=>observer.assumi(Conducente(nome getText,cognome getText,contratto.getValue,turno.getValue,giorno1.getValue,giorno2.getValue,terminale.getValue,numero.getText)))
      contratto.getItems.addAll("Tipo1","Tipo2")
      turno.getItems.addAll("Turno1","Turno2")
      giorno1.getItems.addAll("Lun","Mar","Mer","Gio","Ven")
      giorno2.getItems.addAll("Lun","Mar","Mer","Gio","Ven")
      terminale.getItems.addAll("Cesena","Rimini")
    }
  }
}
