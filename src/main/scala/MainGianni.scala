package view

import javafx.application.Application
import javafx.stage.Stage
<<<<<<< HEAD
import model.Model

=======
import view.scenes.LoginView
>>>>>>> 4bd9665747cee51aec02e0b140e0db110b69d136
private class MainGianni extends Application{
  override def start(primaryStage: Stage): Unit =
    MainGianni.start(primaryStage)
}

object MainGianni extends Application {
  override def start(primaryStage: Stage): Unit = {
<<<<<<< HEAD
    val model: Model = new Model //TurnoTable.TurnoTableRepl()
    val controller: MainController = MainController(model)
    MainView(primaryStage,controller)
  }
=======
>>>>>>> 4bd9665747cee51aec02e0b140e0db110b69d136

    def launch(): Unit = {
      LoginView(primaryStage)
    }
  }
}
