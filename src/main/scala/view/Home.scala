package view

import controller.HomeController
import javafx.application.Application
import javafx.stage.Stage
import scala.language.postfixOps
class  Home() extends Application {
  override def start(primaryStage: Stage): Unit = Home.start(primaryStage)
}
object Home extends Application{
  override def start(primaryStage: Stage): Unit = {
       HomeController(primaryStage)
  }
}
