package view.components

import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.FXML
import javafx.scene.control.Button
import view.scenes.RisorseUmaneLeftObserver
import scala.language.postfixOps

trait RisorseUmaneLeft extends Component[RisorseUmaneLeftObserver]{
}

object RisorseUmaneLeft{
  def apply(): RisorseUmaneLeft = new RisorseUmaneLeftImpl()
  private class RisorseUmaneLeftImpl extends AbstractComponent[RisorseUmaneLeftObserver]("/fxml/components/RisorseUmaneLeft.fxml") with RisorseUmaneLeft{
    @FXML
    var btnAssumi: Button = _
    @FXML
    var btnLicenzia: Button = _
    @FXML
    var btnMalattie: Button = _
    @FXML
    var btnFerie: Button = _
    @FXML
    var btnRecuperoPass: Button = _

    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      btnAssumi.setOnAction(_ => observer loadAssumi)
      btnLicenzia.setOnAction(_ => observer.loadLicenzia)
    }
  }
}
