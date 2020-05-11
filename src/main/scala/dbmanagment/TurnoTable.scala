package dbmanagment
import slick.jdbc.SQLServerProfile.api._
import scala.language.postfixOps
import CaseClassDB._
object TurnoTable{
  class TurnoTableRep(tag: Tag) extends GenericTable[Turno](tag, "TurnoSets","IdTurno") {
    def nomeTurno = column[String]("NomeTurno")
    def fasciaOraria = column[String]("FasciaOraria")
    def * = (nomeTurno, fasciaOraria,id.?).mapTo[Turno]
  }
  val table = TableQuery[TurnoTableRep]
}
