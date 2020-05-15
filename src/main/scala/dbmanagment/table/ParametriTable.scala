package dbmanagment.table
import dbmanagment.setting.GenericTable
import slick.jdbc.SQLServerProfile.api._
import slick.lifted.ProvenShape
import utils.caseclass.CaseClassDB.Parametri
object ParametriTable {
  class ParametriTableRep(tag: Tag) extends GenericTable[Parametri](tag, "ParametriSets","IdParametri") {
    def treSabato: Rep[Byte] = column[Byte]("TreSabato")
    def regola: Rep[String] = column[String]("Regola")
    override def * : ProvenShape[Parametri] = (treSabato,regola,id.?).mapTo[Parametri]

  }
}
