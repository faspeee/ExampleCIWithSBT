package setting
import slick.jdbc.SQLServerProfile.api._

object Zona{
  case class ZonaSet(Zone:String,IdZone:Option[Int]=None)
  // Definition of the SUPPLIERS table
  class Zona(tag: Tag) extends Table[ZonaSet](tag, "ZonaSets") {
    def id = column[Int]("IdZona", O.PrimaryKey,O.AutoInc) // This is the primary key column
    def zona = column[String]("Zone")

    // Every table needs a * projection with the same type as the table's type parameter
    def * = ( zona,id.?).mapTo[ZonaSet]
  }
  val zone = TableQuery[Zona]
}
object Giorno{
  // Definition of the SUPPLIERS table
  case class GiornoSet(Quantita:Int,NomeGiorno:String,IdGiorno:Option[Int]=None)
  class Giorno(tag: Tag) extends Table[GiornoSet](tag, "GiornoSets") {
    def id = column[Int]("IdGiorno", O.PrimaryKey,O.AutoInc) // This is the primary key column
    def quantita = column[Int]("Quantita")
    def nomeGiorno = column[String]("NomeGiorno")
    // Every table needs a * projection with the same type as the table's type parameter
    def * = ( quantita,nomeGiorno,id.?).mapTo[GiornoSet]
  }
  val giorno = TableQuery[Giorno]
}

