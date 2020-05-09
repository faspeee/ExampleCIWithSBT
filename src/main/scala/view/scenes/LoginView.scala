package view.scenes

import java.net.URL
import java.util.ResourceBundle

import controller.{LoginController}
import javafx.fxml.{FXML, Initializable}
import javafx.stage.Stage
import view.components.LoginBox

trait LoginObservervable{
  def sendLogin(name:String, pass:String)
}

object LoginView {
  private class LoginView extends ActiveScene[LoginController] with Initializable with LoginObservervable{

    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      val center = LoginBox()
      center.setObserver(this)
      pane.setCenter(center.pane())
      myController.setView(this)
    }

    override def sendLogin(name: String, pass: String): Unit = {
      myController.login(name, pass)
    }
  }

  def apply(primaryStage:Stage, controller: LoginController) = new LoginView()(primaryStage,controller)

}
