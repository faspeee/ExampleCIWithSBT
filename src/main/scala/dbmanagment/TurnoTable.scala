package dbmanagment
import slick.jdbc.SQLServerProfile.api._
import scala.language.postfixOps
import CaseClassDB._
object TurnoTable{
<<<<<<< HEAD

  private case class Turno(nomeTurno: String, fasciaOraria: String,id: Option[Int] = None)

  private class TurnoTableRep(tag: Tag) extends Table[Turno](tag, "TurnoSets") {
    def id = column[Int]("idTurno",O.PrimaryKey,O.AutoInc)

=======
  class TurnoTableRep(tag: Tag) extends GenericTable[Turno](tag, "TurnoSets","IdTurno") {
>>>>>>> 4bd9665747cee51aec02e0b140e0db110b69d136
    def nomeTurno = column[String]("NomeTurno")
    def fasciaOraria = column[String]("FasciaOraria")
    def * = (nomeTurno, fasciaOraria,id.?).mapTo[Turno]
  }
  val table = TableQuery[TurnoTableRep]
}
