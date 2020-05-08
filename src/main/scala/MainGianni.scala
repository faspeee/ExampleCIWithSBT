package view

import controller.MainController
import dbmanagment.TurnoTable
import javafx.application.Application
import javafx.stage.Stage

private class MainGianni extends Application{
  override def start(primaryStage: Stage): Unit =
    MainGianni.start(primaryStage)
}

object MainGianni extends Application{
  override def start(primaryStage: Stage): Unit = {
    val model:TurnoTable = TurnoTable()
    val controller: MainController = MainController(model)
    MainView(primaryStage,controller)
  }

  def launch(): Unit = {
    Application.launch()
  }
}