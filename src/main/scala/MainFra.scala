
import controller.FraController
import javafx.application.Application
import javafx.stage.Stage
import model.Model
import view.MainView


object MainFra extends App {

  val model: Model = new Model
  val controller = FraController(model)

}
