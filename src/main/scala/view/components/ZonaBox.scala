package view.components

import java.net.URL
import java.util.ResourceBundle

import utils.caseclass.CaseClassDB.Zona
import javafx.fxml.FXML
import javafx.scene.control.{Button, ProgressIndicator, TextField}
import javafx.scene.layout.VBox
import view.scenes.ZonaBoxObserver

trait ZonaBox extends Component[ZonaBoxObserver]{
  def setZone(zone:List[Zona])
  def loading()
}

trait ZoneHBoxObserver{
  def select(id:Int)
}
object ZonaBox {
  def apply(): ZonaBox = new ZonaBoxImpl()

  private class ZonaBoxImpl()extends AbstractComponent[ZonaBoxObserver]("/fxml/components/ZonaBox.fxml") with ZonaBox with ZoneHBoxObserver {
    @FXML
    var zonaBox:VBox = _
    @FXML
    var nomeZona:TextField = _
    @FXML
    var insert:Button = _
    @FXML
    var remove:Button = _
    var toRemove:Set[Int] = Set()

    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      insert.setOnAction(_=> observer.insert(nomeZona.getText()))
      remove.setOnAction(_=> observer.remove(toRemove))
    }

    override def setZone(zone: List[Zona]): Unit = {
      zone.foreach(z => {
        val zona = ZoneHBox()
        zona.setObserver(this)
        zona.setZona(z)
        zonaBox.getChildren.add(2,zona.pane)
      })
    }

    override def select(id: Int): Unit = {
      toRemove.contains(id)match {
        case true => toRemove = toRemove filter(_!=id)
        case _ => toRemove = toRemove + id
      }
    }

    override def loading(): Unit = {
      zonaBox.getChildren.add(2, new ProgressIndicator())
    }
  }
}
