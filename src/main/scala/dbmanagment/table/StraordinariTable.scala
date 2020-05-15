package dbmanagment.table
import java.sql.Date
import dbmanagment.setting.GenericTable
import dbmanagment.table.PersonaTable.PersonaTableRep
import dbmanagment.table.TurnoTable.TurnoTableRep
import slick.jdbc.SQLServerProfile.api._
import slick.lifted.{ForeignKeyQuery, ProvenShape}
import utils.caseclass.CaseClassDB.{Persona, Straordinari, Turno}
object StraordinariTable {
  class StraordinariTableRep(tag: Tag) extends GenericTable[Straordinari](tag, "StraordinariSets","IdStraordinari") {
    def data: Rep[Date] = column[Date]("Data")
    def personaId: Rep[Int] = column[Int]("Persone_Matricola")
    def turnoId: Rep[Int] = column[Int]("Turno_IdTurno")
    override def * : ProvenShape[Straordinari] = (data,personaId,turnoId,id.?).mapTo[Straordinari]
    def persona: ForeignKeyQuery[PersonaTableRep, Persona] = foreignKey("Persone_Matricola", personaId, TableQuery[PersonaTableRep])(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.SetNull)
    def turno: ForeignKeyQuery[TurnoTableRep, Turno] = foreignKey("Turno_IdTurno", turnoId, TableQuery[TurnoTableRep])(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.SetNull)

  }
}
