package view.components
import javafx.scene.layout.Pane
import view.loaders.ComponentLoader

trait Component[A] {
  def pane():Pane
  def setObserver(observer:A)
}

abstract class AbstractComponent[A](val path:String) extends Component[A]{
  protected var observer:A = _
  private val loadedPane:Pane = ComponentLoader.loadView(this,path)

  override def pane(): Pane = {
    loadedPane
  }

  override def setObserver(observer: A): Unit =
    this.observer = observer
}