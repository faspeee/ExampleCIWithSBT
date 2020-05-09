package view.components
import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.{Button, TextField}
import view.scenes.{ LoginObservervable}

import scala.language.postfixOps

object LoginBox {

  def apply(): Component[LoginObservervable] = new LoginBoxView()

  private class LoginBoxView extends AbstractComponent[LoginObservervable]("/fxml/components/LoginBox.fxml") with Initializable{
    @FXML
    var usernameField: TextField = _
    @FXML
    var passwordField: TextField = _
    @FXML
    var loginButton: Button = _

    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      loginButton.setOnAction(_ => {
        val name = usernameField.getText()
        val pass = passwordField.getText()
        observer.sendLogin(name,pass)
      })
    }
  }
}
