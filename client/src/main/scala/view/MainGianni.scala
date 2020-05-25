package view
import javafx.application.Application
import javafx.stage.Stage
import view.scenes.LoginView


object MainGianni extends App{
  private class MainGianni extends Application {
    override def start(primaryStage: Stage): Unit = {
      LoginView(primaryStage)
    }
  }
  Application.launch(classOf[MainGianni])
}