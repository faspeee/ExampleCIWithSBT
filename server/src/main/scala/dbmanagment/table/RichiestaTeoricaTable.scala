package dbmanagment.table
import java.sql.Date

import dbmanagment.setting.GenericTable
import slick.jdbc.SQLServerProfile.api._
import slick.lifted.ProvenShape
import utils.caseclass.CaseClassDB.RichiestaTeorica
object RichiestaTeoricaTable {
  class RichiestaTeoricaTableRep(tag: Tag) extends GenericTable[RichiestaTeorica](tag, "RichiestaTeoricaSets","IdRichiestaTeorica") {
    def dataInizio: Rep[Date] = column[Date]("DataInizio")
    def dataFine: Rep[Date] = column[Date]("DataFine")
    override def * : ProvenShape[RichiestaTeorica] = (dataInizio,dataFine,id.?).mapTo[RichiestaTeorica]

  }
}
