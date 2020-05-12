package dbmanagment
import slick.jdbc.SQLServerProfile.api._
import scala.language.postfixOps
import CaseClassDB._
object TerminaleTable{
  class TerminaleTableRep(tag: Tag) extends GenericTable[Terminale](tag, "TurnoSets","IdTurno") {
    def nomeTerminale = column[String]("NomeTerminale")
    def * = (nomeTerminale,id.?).mapTo[Terminale]
  }
}
