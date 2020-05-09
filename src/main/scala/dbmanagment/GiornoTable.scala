package dbmanagment


import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.Future
/**
 * Manage the Turno table of the db.
 */
trait GiornoTable {
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

object GiornoTable{
  case class Giorno(Quantita:Int,NomeGiorno:String,IdGiorno:Option[Int]=None)
  class GiornoTableRep(tag: Tag) extends GenericTable[Giorno](tag, "GiornoSets","IdGiorno"){
    def quantita = column[Int]("Quantita")
    def nomeGiorno = column[String]("NomeGiorno")
    // Every table needs a * projection with the same type as the table's type parameter
    def * = ( quantita,nomeGiorno,id.?).mapTo[Giorno]
  }
}
