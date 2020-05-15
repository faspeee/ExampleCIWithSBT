package dbmanagment.table
import dbmanagment.setting.GenericTable
import dbmanagment.table.GiornoTable.GiornoTableRep
import dbmanagment.table.SettimaneTable.SettimaneTableRep
import dbmanagment.table.TurnoTable.TurnoTableRep
import slick.jdbc.SQLServerProfile.api._
import slick.lifted.{ForeignKeyQuery, ProvenShape}
import utils.caseclass.CaseClassDB.{Giorno, GiornoInSettimana, Settimane, Turno}

object GiornoInSettimanaTable {
  class GiornoInSettimanaTableRep(tag: Tag) extends GenericTable[GiornoInSettimana](tag, "GiornoInSettimanaSets","IdSettimana") {
    def giornoId: Rep[Int] = column[Int]("GiornoIdGiorno")
    def turnoId: Rep[Int] = column[Int]("Turno_IdTurno")
    def parametriId: Rep[Int] = column[Int]("Parametri_IdParametri")
    def settimanaId: Rep[Int] = column[Int]("SettimaneIdSettimane")
    override def * : ProvenShape[GiornoInSettimana] = (giornoId,turnoId,parametriId,settimanaId,id.?).mapTo[GiornoInSettimana]
    def giorno: ForeignKeyQuery[GiornoTableRep, Giorno] = foreignKey("GiornoIdGiorno", giornoId, TableQuery[GiornoTableRep])(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
    def turno: ForeignKeyQuery[TurnoTableRep, Turno] = foreignKey("Turno_IdTurno", turnoId, TableQuery[TurnoTableRep])(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
    def settimana: ForeignKeyQuery[SettimaneTableRep, Settimane] = foreignKey("SettimaneIdSettimane", settimanaId, TableQuery[SettimaneTableRep])(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)

  }
}
