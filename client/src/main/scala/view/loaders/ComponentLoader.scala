package view.loaders

import javafx.fxml.FXMLLoader
import javafx.scene.layout.Pane
import view.components.Component

/**
 * Helper per caricare un componente.
 */
object ComponentLoader {
  /**
   * È generico e gli serve il controller da impostare per l'XML caricato
   * (di solito this di chi chiama) e il path del FXML.
   *
   * @param controller
   *                   Il controller del FXML
   * @param layoutPath
   *                   Il percorso del FXML
   * @return
   *         Un [[javafx.scene.layout.Pane]], caricato da FXML
   */
  def loadView(controller: Component[_], layoutPath: String): Pane = {
    val loader = new FXMLLoader(getClass.getResource(layoutPath))
    loader.setController(controller)
    loader.load()
  }
}
