package dbmanagment
import slick.jdbc.SQLServerProfile.api._
import CaseClassDB._

import scala.concurrent.Future
object ZonaTable{
  class ZonaTableRep(tag: Tag) extends GenericTable[Zona](tag, "ZonaSets","IdZona") {
    def Zones = column[String]("Zone")
    // Every table needs a * projection with the same type as the table's type parameter
    def * = ( Zones,id.?).mapTo[Zona]
  }

}
