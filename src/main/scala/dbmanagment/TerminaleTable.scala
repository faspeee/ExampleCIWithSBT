package dbmanagment
import slick.jdbc.SQLServerProfile.api._

import scala.language.postfixOps
import CaseClassDB._
import dbmanagment.ZonaTable.ZonaTableRep

object TerminaleTable{
  class TerminaleTableRep(tag: Tag) extends GenericTable[Terminale](tag, "TerminalSets","IdTerminale") {
    def zonaId = column[Int]("ZonaIdZona")
    def nomeTerminale = column[String]("NomeTerminale")
    def * = (nomeTerminale,zonaId,id.?).mapTo[Terminale]
    def zona = foreignKey("ZonaIdZona", zonaId, TableQuery[ZonaTableRep])(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)

  }
}