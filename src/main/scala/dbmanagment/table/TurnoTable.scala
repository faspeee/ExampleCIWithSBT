package dbmanagment.table
import slick.jdbc.SQLServerProfile.api._
import dbmanagment.setting.GenericTable
import slick.lifted.ProvenShape
import utils.caseclass.CaseClassDB.Turno

object TurnoTable{
  class TurnoTableRep(tag: Tag) extends GenericTable[Turno](tag, "TurnoSets","IdTurno") {
    def nomeTurno: Rep[String] = column[String]("NomeTurno")
    def fasciaOraria: Rep[String] = column[String]("FasciaOraria")
    override def * : ProvenShape[Turno] = (nomeTurno, fasciaOraria,id.?).mapTo[Turno]
  }
}
