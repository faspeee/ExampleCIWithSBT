package dbmanagment
import slick.jdbc.SQLServerProfile.api._
import CaseClassDB._
object GiornoTable{
  class GiornoTableRep(tag: Tag) extends GenericTable[Giorno](tag, "GiornoSets","IdGiorno"){
    def quantita = column[Int]("Quantita")
    def nomeGiorno = column[String]("NomeGiorno")
    // Every table needs a * projection with the same type as the table's type parameter
    def * = ( quantita,nomeGiorno,id.?).mapTo[Giorno]
  }
}
