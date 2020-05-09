//package view.components
//
//import java.net.URL
//import java.util.ResourceBundle
//
//import javafx.fxml.{FXML, Initializable}
//import javafx.scene.control.{Button, TextField}
//import view.scenes.TopObserver
//
//import scala.language.postfixOps
//
//
//object TopHBoxView {
//
//  def apply(): Component[TopObserver] = new TopHBoxView()
//
//  private class TopHBoxView extends AbstractComponent[TopObserver]("/fxml/components/TopHBox.fxml") with Initializable {
//    @FXML
//    var insert:Button = _
//    @FXML
//    var nomeGiorno:TextField = _
//    @FXML
//    var fasciaOraria:TextField = _
//
//    override def initialize(location: URL, resources: ResourceBundle): Unit = {
//      insert.setOnAction(_ =>{
//        observer.turnoAdded(nomeGiorno getText, fasciaOraria getText)
//      })
//    }
//  }
//}
