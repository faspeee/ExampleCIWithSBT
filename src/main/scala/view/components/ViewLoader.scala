package view.components

import javafx.fxml.FXMLLoader
import javafx.scene.layout.Pane

object ViewLoader {
  def loadView(controller: Component[_], layoutPath: String): Pane = {
    val loader = new FXMLLoader(getClass.getResource(layoutPath))
    loader.setController(controller)
    loader.load()
  }
}