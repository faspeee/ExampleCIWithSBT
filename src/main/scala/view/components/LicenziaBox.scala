package view.components

import java.net.URL
import java.util.ResourceBundle

import utils.caseclass.CaseClassDB.Persona
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import view.scenes.LicenziaBoxObserver

trait LicenziaBox extends Component[LicenziaBoxObserver]{
  def setLista(licenziandi:List[Persona])
}

trait ConducenteObserver{
  def select(id:Int)
}

object LicenziaBox{
  def apply(): LicenziaBox = new LicenziaBoxImpl()
  private class LicenziaBoxImpl extends AbstractComponent[LicenziaBoxObserver]("/fxml/components/LicenziaBox.fxml") with LicenziaBox with ConducenteObserver {
    @FXML
    var licenziaBox:VBox = _
    @FXML
    var benedici:Button = _
    var daLicenziare:Set[Int] = Set()

    override def initialize(location: URL, resources: ResourceBundle): Unit =
      benedici.setOnAction(_ => observer.licenzia(daLicenziare))

    override def setLista(licenziandi: List[Persona]): Unit = {
      licenziandi.foreach(f => {
        val conducente = ConducenteBox()
        conducente.setObserver(this)
        conducente.setConducente(f)
        licenziaBox.getChildren.add(1,conducente.pane)
      })
    }

    override def select(id: Int): Unit = {
      println(id)
      daLicenziare.contains(id) match {
        case true => daLicenziare = daLicenziare filter(_ != id)
        case _ => daLicenziare = daLicenziare + id
      }
    }
  }
}
