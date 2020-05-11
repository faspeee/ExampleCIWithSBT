package view.components

import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.FXML
import javafx.scene.control.{CheckBox, Label}
import mock.Conducente

trait ConducenteBox extends Component[ConducenteObserver]{
  def setConducente(conducente:Conducente)
}

object ConducenteBox{
  def apply (): ConducenteBox = new ConducenteBoxImpl()

  private class ConducenteBoxImpl() extends AbstractComponent [ConducenteObserver]("/fxml/components/conducenteBox.fxml") with ConducenteBox {
    @FXML
    var id:Label = _
    @FXML
    var nome:Label = _
    @FXML
    var cognome:Label = _
    @FXML
    var contratto:Label = _
    @FXML
    var data:Label = _
    @FXML
    var selezionato: CheckBox = _

    override def setConducente(conducente: Conducente): Unit = {
      id.setText(conducente.telefono)
      nome.setText(conducente.nome)
      cognome.setText(conducente.cognome)
      contratto.setText(conducente.contratto)
      data.setText(conducente.terminale)
    }

    override def initialize(location: URL, resources: ResourceBundle): Unit =
      selezionato.setOnAction(_ => observer.select(id.getText.toString))
  }
}