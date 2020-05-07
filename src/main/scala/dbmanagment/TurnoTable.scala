package dbmanagment
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.postfixOps
import scala.util.Success

/**
 * Manage the Turno table of the db.
 */
trait TurnoTable {
  /**
   * Inserts a Turno in the database, getting the name and the fascia oraria and
   * returning the id of the inserted turno
   * @param nomeTurno
   *                  Name of the turno to insert.
   * @param fasciaOraria
   *                     Fascia oraria of the turno to insert.
   * @return
   *         A [[scala.concurrent.Future]] of the id of the inserted Turno.
   */
  def insertTurno(nomeTurno:String, fasciaOraria: String): Future[Int]
}

object TurnoTable{
  def apply(): TurnoTable = new TurnoTableImpl()

  private class TurnoTableImpl extends TurnoTable{
    private val db = DBConnection.db()
    override def insertTurno(nomeTurno: String, fasciaOraria: String): Future[Int] = {
      val insertId = (turni returning turni.map(_.id)) += Turno(nomeTurno,fasciaOraria)
      db.run(insertId)
    }
  }

  private case class Turno(nomeTurno: String, fasciaOraria: String,id: Option[Int] = None)
  private class TurnoTableRep(tag: Tag) extends Table[Turno](tag, "TurnoSets") {
    def id = column[Int]("idTurno",O.PrimaryKey,O.AutoInc)

    def nomeTurno = column[String]("NomeTurno")

    def fasciaOraria = column[String]("FasciaOraria")

    def * = (nomeTurno, fasciaOraria,id.?).mapTo[Turno]
  }

  private val turni = TableQuery[TurnoTableRep]
}
