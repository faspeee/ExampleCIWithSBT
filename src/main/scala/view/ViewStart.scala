package view

import controller.MainController
import javafx.application.Application
import javafx.stage.Stage

class ViewStart extends Application{
  override def start(primaryStage: Stage): Unit =
    ViewStart.start(primaryStage)
}

object ViewStart extends Application{
  override def start(primaryStage: Stage): Unit = {
    MainController(primaryStage)
  }

  def launch(): Unit = {
    Application.launch()
  }
}