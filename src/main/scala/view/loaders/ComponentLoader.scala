package view.loaders

import javafx.fxml.FXMLLoader
import javafx.scene.layout.Pane
import view.components.Component

object ComponentLoader {
  def loadView(controller: Component[_], layoutPath: String): Pane = {
    val loader = new FXMLLoader(getClass.getResource(layoutPath))
    loader.setController(controller)
    loader.load()
  }
}
