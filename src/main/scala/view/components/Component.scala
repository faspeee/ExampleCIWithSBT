package view.components
import javafx.fxml.Initializable
import javafx.scene.layout.Pane
import view.loaders.ComponentLoader

/**
 * Modella un componente interno alla Scena attiva. Possono essere montati uno dentro l'altro(credo).
 * Generico per permettere di impostare un observer adeguato
 * @tparam A
 *           L'observer che lo controlla
 */
trait Component[A] {
  /**
   * Restituisce il pannello caricato da FXML
   * @return
   *         il pannello caricato da FXML
   */
  def pane():Pane

  /**
   * Setta un observer per questo componente
   * @param observer
   *                 L'observer del componente
   */
  def setObserver(observer:A)
}

/**
 * Classe astratta per il trait [[view.components.Component]]
 * @param path
 * @tparam A
 *           L'observer che lo controlla, generico.
 */
abstract class AbstractComponent[A](val path:String) extends Component[A] with Initializable{
  protected var observer:A = _
  private val loadedPane:Pane = ComponentLoader.loadView(this,path)

  override def pane(): Pane = {
    loadedPane
  }

  override def setObserver(observer: A): Unit =
    this.observer = observer
}