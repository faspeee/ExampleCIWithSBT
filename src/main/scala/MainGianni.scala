package view

import controller.MainController
import dbmanagment.TurnoTable
import javafx.application.Application
import javafx.stage.Stage
import model.Model

private class MainGianni extends Application{
  override def start(primaryStage: Stage): Unit =
    MainGianni.start(primaryStage)
}

object MainGianni extends Application{
  override def start(primaryStage: Stage): Unit = {
    val model: Model = new Model //TurnoTable.TurnoTableRepl()
    val controller: MainController = MainController(model)
    MainView(primaryStage,controller)
  }

  def launch(): Unit = {
    Application.launch()
  }
}