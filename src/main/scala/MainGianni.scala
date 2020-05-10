package view

import javafx.application.Application
import javafx.stage.Stage
import view.scenes.LoginView
private class MainGianni extends Application{
  override def start(primaryStage: Stage): Unit =
    MainGianni.start(primaryStage)
}

object MainGianni extends Application{
  override def start(primaryStage: Stage): Unit = {

  def launch(): Unit = {
    LoginView(primaryStage)
  }
}
