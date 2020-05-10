package view.scenes

import java.net.URL
import java.util.ResourceBundle

import controller.LoginController
import javafx.fxml.Initializable
import javafx.stage.Stage
import view.components.{LoginBox}

/**
 * Metodi messi a disposizione del [[controller.Controller]] che lo controlla
 */
trait LoginObservervable{
  /**
   * Gestione del login errato
   */
  def errorLogin()

  /**
   * Gestione del login buono. Se viene chiamato nasconde il suo [[javafx.stage.Stage]] e crea quello successivo
   */
  def goodLogin()
}

/**
 * Trait per specificare che è un observer per i suoi figli... ne deve avere 1 per figlio?
 */
trait LoginObserver{
  /**
   * Metodo che chiama il figlio qundo viene fatto login. Per gestire questo viene chiamato il [[controller.Controller]]
   * @param name
   * @param pass
   */
  def sendLogin(name:String, pass:String)
}

/**
 * Crea una LoginView, che implementa [[view.scenes.AbstractActiveScene]], [[view.scenes.LoginObservervable]] e
 * [[view.scenes.LoginObserver]]
 */
object LoginView {
  private class LoginView extends AbstractActiveScene with Initializable with LoginObservervable with LoginObserver {
    private val myController = LoginController()
    private var center:LoginBox = _

    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      myController.setView(this)
      center = LoginBox()
      center.setObserver(this)
      pane.setCenter(center.pane())
    }

    override def sendLogin(name: String, pass: String): Unit = {
      myController.login(name, pass)
    }

    override def errorLogin(): Unit =
      center.errorLog()

    override def goodLogin(): Unit ={
      stage.hide
      TestTurnoView(new Stage(),Option(stage))
    }
  }

  def apply(primaryStage:Stage) = new LoginView()(primaryStage)

}
