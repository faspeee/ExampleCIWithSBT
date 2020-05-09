//package view.scenes
//
//import java.net.URL
//import java.util.ResourceBundle
//
//import javafx.fxml.{FXML, Initializable}
//import javafx.scene.control.Button
//import javafx.scene.layout.BorderPane
//import javafx.stage.Stage
//import view.components.TopHBoxView
//import view.loaders.SceneLoader
//
//import scala.language.postfixOps
//
//trait TopObserver{
//  def turnoAdded(nomeTurno:String, fasciaOraria:String)
//}
//
//object MainView{
//  private class MainViewImpl extends ActiveScene[MainController] with Initializable with TopObserver{
//
//    override def turnoAdded(nomeTurno: String, fasciaOraria: String): Unit =
//      myController.turnoAdded(nomeTurno,fasciaOraria)
//  }
//  def apply(primaryStage:Stage, controller: MainController) = new MainViewImpl()(primaryStage,controller)
//}
