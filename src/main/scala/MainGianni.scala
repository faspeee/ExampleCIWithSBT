package view

import controller.MainController
import dbmanagment.TurnoTable
import javafx.application.Application
import javafx.stage.Stage
import setting.TestDb.TurnoTable

private class MainGianni extends Application{
  override def start(primaryStage: Stage): Unit =
    MainGianni.start(primaryStage)
}

object MainGianni extends Application{
  override def start(primaryStage: Stage): Unit = {

  }

  def launch(): Unit = {
    Application.launch()
  }
}