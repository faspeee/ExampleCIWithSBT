package dbmanagment.table
import dbmanagment.table.ParametriTable.ParametriTableRep
import dbmanagment.setting.GenericTable
import slick.jdbc.SQLServerProfile.api._
import slick.lifted.{ForeignKeyQuery, ProvenShape}
import utils.caseclass.CaseClassDB.{Settimane,Parametri}
object SettimaneTable {
  class SettimaneTableRep(tag: Tag) extends GenericTable[Settimane](tag, "SettimaneSet","IdSettimane") {
    def parametriSetId: Rep[Int] = column[Int]("ParametriSetIdParametri")
    override def * : ProvenShape[Settimane] = (parametriSetId,id.?).mapTo[Settimane]
    def parametriSet: ForeignKeyQuery[ParametriTableRep, Parametri] = foreignKey("ParametriSetIdParametri", parametriSetId, TableQuery[ParametriTableRep])(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
  }
}