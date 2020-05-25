package view.components
import java.net.URL
import java.util.ResourceBundle

import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.{Button, Label, TextField}
import view.scenes.LoginObserver

import scala.language.postfixOps

/**
 * Operazioni che il componente espone al suo observer.
 * Estende un [[view.components.Component]] dicendo il tipo del suo observer,
 * in questo caso un [[view.scenes.LoginObserver]]
 */
trait LoginBox extends Component[LoginObserver]{
  /**
   * Se viene chiamato, setta un mesaaggio d'errore sulla label interna.
   */
  def errorLog()
}

/**
 * Comapionion del trait [[view.components.LoginBox]]
 */
object LoginBox {

  def apply(): LoginBox = new LoginBoxView()

  private class LoginBoxView extends AbstractComponent[LoginObserver]("/fxml/components/loginBox.fxml") with Initializable with LoginBox {
    @FXML
    var usernameField: TextField = _
    @FXML
    var passwordField: TextField = _
    @FXML
    var loginButton: Button = _
    @FXML
    var error: Label = _

    override def initialize(location: URL, resources: ResourceBundle): Unit = {

      loginButton.setOnAction(_ => {
        val name = usernameField.getText()
        val pass = passwordField.getText()
        observer.sendLogin(name,pass)
      })
    }

    override def errorLog(): Unit =
      error.setText("Errore nel login, user o pass errate")

  }
}
