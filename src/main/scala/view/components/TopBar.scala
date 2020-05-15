package view.components

import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.FXML
import javafx.scene.control.Button
import view.scenes.AbstractActiveSceneWithTop

trait TopBar extends Component[AbstractActiveSceneWithTop]{
}

object TopBar{
  def apply(): TopBar = new TopBarImpl()

  private class TopBarImpl() extends AbstractComponent[AbstractActiveSceneWithTop]("/fxml/components/TopBar.fxml") with TopBar{
    @FXML
    var risorse:Button = _
    @FXML
    var zone:Button = _

    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      risorse.setOnAction(_ => observer.risorse())
      zone.setOnAction(_=> observer.zone())
    }
  }
}
