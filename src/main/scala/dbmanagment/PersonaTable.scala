package dbmanagment
import slick.jdbc.SQLServerProfile.api._
import scala.language.postfixOps
import CaseClassDB._
object PersonaTable{
  class PersonaTableRep(tag: Tag) extends GenericTable[Persona](tag, "PersoneSets","Matricola") {
    def nome = column[String]("Nome")
    def cognome = column[String]("Cognome")
    def dataNascita = column[java.sql.Date]("DataNascita")
    def numTelefono = column[String]("NumTelefono")
    def ruolo = column[Int]("Ruolo")
    def * = (nome, cognome,dataNascita,numTelefono,ruolo,id.?).mapTo[Persona]
  }
}