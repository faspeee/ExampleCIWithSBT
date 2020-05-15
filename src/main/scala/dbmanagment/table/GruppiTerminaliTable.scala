package dbmanagment.table
import dbmanagment.setting.GenericTable
import dbmanagment.table.ParametriTable.ParametriTableRep
import slick.jdbc.SQLServerProfile.api._
import slick.lifted.{ForeignKeyQuery, ProvenShape}
import utils.caseclass.CaseClassDB.{GruppiTerminali, Parametri}
object GruppiTerminaliTable {
  class GruppiTerminaliTableRep(tag: Tag) extends GenericTable[GruppiTerminali](tag, "GruppiTerminaliSets","IdGruppoTerminale") {
    def parametriId: Rep[Int] = column[Int]("ParametriIdParametri")
    override def * : ProvenShape[GruppiTerminali] = (parametriId,id.?).mapTo[GruppiTerminali]
    def parametri: ForeignKeyQuery[ParametriTableRep, Parametri] = foreignKey("ParametriIdParametri", parametriId, TableQuery[ParametriTableRep])(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)

  }
}