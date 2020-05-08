package dbmanagment

import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.Future

/**
 * Manage the Turno table of the db.
 */
trait ZonaTable{
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
object ZonaTable{
  def apply(): ZonaTable = new ZonaTableImpl()

  private class ZonaTableImpl extends ZonaTable{
    private val db = DBConnection.db()
    override def insertTurno(nomeTurno: String, fasciaOraria: String): Future[Int] = ???
  }

  case class Zona(Zones:String,IdZone:Option[Int]=None)
  class ZonaTableRep(tag: Tag) extends Table[Zona](tag, "ZoneSets") {
    def id = column[Int]("IdZona", O.PrimaryKey,O.AutoInc) // This is the primary key column
    def zona = column[String]("Zone")

    // Every table needs a * projection with the same type as the table's type parameter
    def * = ( zona,id.?).mapTo[Zona]
  }
  val zone = TableQuery[ZonaTableRep]
}
