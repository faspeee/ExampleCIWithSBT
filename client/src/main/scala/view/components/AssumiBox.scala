package view.components

import java.net.URL
import java.util.ResourceBundle

import utils.caseclass.CaseClassDB.{Persona, Terminale}
import javafx.fxml.FXML
import javafx.scene.control.{Button, ComboBox, TextField}
import view.scenes.AssumiBoxObserver

import scala.language.postfixOps

trait AssumiBox extends Component[AssumiBoxObserver]{
  def setTerminali(l:List[Terminale])
}

object AssumiBox{
  def apply(): AssumiBox = new AssumiBoxImpl()

  private class AssumiBoxImpl() extends AbstractComponent[AssumiBoxObserver]("/fxml/components/assumiBox.fxml") with AssumiBox{
    @FXML
    var nome: TextField = _
    @FXML
    var cognome: TextField = _
    @FXML
    var contratto: ComboBox[String] = _
    @FXML
    var turno: ComboBox[String] = _
    @FXML
    var giorno1: ComboBox[String] = _
    @FXML
    var giorno2: ComboBox[String] = _
    @FXML
    var terminale: ComboBox[String] = _
    @FXML
    var numero: TextField = _
    @FXML
    var salva: Button = _
    var terminali = List[Terminale]()

    override def initialize(location: URL, resources: ResourceBundle): Unit = {
      salva.setOnAction(_=>observer.assumi(Persona(nome getText,cognome getText,new java.sql.Date(1000000),numero.getText,0,id)))
      contratto.getItems.addAll("Tipo1","Tipo2")
      turno.getItems.addAll("Turno1","Turno2")
      giorno1.getItems.addAll("Lun","Mar","Mer","Gio","Ven")
      giorno2.getItems.addAll("Lun","Mar","Mer","Gio","Ven")
    }

    override def setTerminali(l: List[Terminale]): Unit = {
      terminali = l
      l.flatMap(t=>List(t.nomeTerminale)).foreach(t => terminale.getItems.add(t))
    }

    private def id():Option[Int] =
      terminali.filter(_.nomeTerminale == terminale.getValue).head.idTerminale

  }
}
