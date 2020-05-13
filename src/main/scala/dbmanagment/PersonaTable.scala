package dbmanagment
import slick.jdbc.SQLServerProfile.api._
import scala.language.postfixOps
import CaseClassDB._
import dbmanagment.TerminaleTable.TerminaleTableRep
object PersonaTable{
  class PersonaTableRep(tag: Tag) extends GenericTable[Persona](tag, "PersoneSets","Matricola") {
    def terminaleId = column[Int]("Terminale_IdTerminale")
    def nome = column[String]("Nome")
    def cognome = column[String]("Cognome")
    def dataNascita = column[java.sql.Date]("DataNascita")
    def numTelefono = column[String]("NumTelefono")
    def ruolo = column[Int]("Ruolo")
    def * = (nome, cognome,dataNascita,numTelefono,ruolo,terminaleId.?,id.?).mapTo[Persona]
    def terminale = foreignKey("Terminale_IdTerminale", terminaleId, TableQuery[TerminaleTableRep])(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
  }
}