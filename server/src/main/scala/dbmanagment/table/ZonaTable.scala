package dbmanagment.table

import slick.jdbc.SQLServerProfile.api._
import dbmanagment.setting.GenericTable
import slick.lifted.ProvenShape
import utils.caseclass.CaseClassDB.Zona

object ZonaTable{
  class ZonaTableRep(tag: Tag) extends GenericTable[Zona](tag, "ZonaSets","IdZona") {
    def Zones: Rep[String] = column[String]("Zone")
    // Every table needs a * projection with the same type as the table's type parameter
    override def * : ProvenShape[Zona] = ( Zones,id.?).mapTo[Zona]
  }

}
