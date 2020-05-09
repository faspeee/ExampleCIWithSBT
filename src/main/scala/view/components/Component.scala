package view.components

import javafx.fxml.FXMLLoader
import javafx.scene.layout.Pane

trait Component[A] {
  def pane():Pane
  def setObserver(observer:A)
}

abstract class AbstractComponent[A](val path:String) extends Component[A]{
  protected var observer:A = _
  private val loadedPane:Pane = ViewLoader.loadView(this,path)

  override def pane(): Pane = {
    loadedPane
  }

  override def setObserver(observer: A): Unit =
    this.observer = observer

}