package view.components

import java.net.URL
import java.util.ResourceBundle

import utils.caseclass.CaseClassDB.Zona
import javafx.fxml.FXML
import javafx.scene.control.{CheckBox, Label}

trait ZoneHBox extends Component[ZoneHBoxObserver]{
  def setZona(zona:Zona)
}
object ZoneHBox {
  def apply(): ZoneHBox = new ZoneHBoxImpl()

  private class ZoneHBoxImpl() extends AbstractComponent[ZoneHBoxObserver]("/fxml/components/zoneHBox.fxml") with ZoneHBox{
    @FXML
    var id:Label = _
    @FXML
    var nomeZona:Label = _
    @FXML
    var selezionato: CheckBox = _

    override def setZona(zona: Zona): Unit = {
      id.setText(zona.idZone.get.toString)
      nomeZona.setText(zona.zones)
    }

    override def initialize(location: URL, resources: ResourceBundle): Unit =
      selezionato.setOnAction(_=>observer.select(id.getText.toInt))
  }
}
