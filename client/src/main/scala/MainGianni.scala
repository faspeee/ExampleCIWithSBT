import javafx.application.Application
import javafx.stage.Stage
import view.scenes.LoginView

private class MainGianni extends Application{
  override def start(primaryStage: Stage): Unit =
    LoginView(primaryStage)
}

object MainClient extends App{
  Application.launch(classOf[MainGianni])
}