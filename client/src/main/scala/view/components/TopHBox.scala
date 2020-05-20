package view.components
import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.{Button, TextField}
import view.scenes.TopHBoxObserver

import scala.language.postfixOps

/**
 * Sta roba funziona come [[view.components.LoginBox]]... va fatto un
 * refactor? non saprei che pattern usare :/
 */
trait TopHBox extends Component[TopHBoxObserver]{
}

/**
 * Companion del trait [[view.components.TopHBox]]
 */
object TopHBox{
  def apply(): TopHBox = new TopHBoxImpl()

  private class TopHBoxImpl() extends AbstractComponent[TopHBoxObserver]("/fxml/components/TopHBox.fxml") with TopHBox{
    @FXML
    var nomeGiorno: TextField = _
    @FXML
    var fasciaOraria: TextField = _
    @FXML
    var insert: Button = _

    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      insert.setOnAction(_ =>{
        observer.insertTurno(nomeGiorno.getText(),fasciaOraria.getText())
      })
    }
  }
}
