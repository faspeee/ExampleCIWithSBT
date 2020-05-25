
import controller.FraController
import javafx.application.Application
import javafx.stage.Stage
import model.Model


object MainFra  {

  val model: Model = new Model
  val controller = FraController(model)

}
