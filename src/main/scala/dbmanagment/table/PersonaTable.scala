package dbmanagment.table
import java.sql.Date

import slick.jdbc.SQLServerProfile.api._
import dbmanagment.setting.GenericTable
import dbmanagment.table.TerminaleTable.TerminaleTableRep
import slick.lifted.{ForeignKeyQuery, ProvenShape}
import utils.caseclass.CaseClassDB.{Persona, Terminale}

object PersonaTable{
  class PersonaTableRep(tag: Tag) extends GenericTable[Persona](tag, "PersoneSets","Matricola") {
    def terminaleId: Rep[Int] = column[Int]("Terminale_IdTerminale")
    def nome: Rep[String] = column[String]("Nome")
    def cognome: Rep[String] = column[String]("Cognome")
    def dataNascita: Rep[Date] = column[java.sql.Date]("DataNascita")
    def numTelefono: Rep[String] = column[String]("NumTelefono")
    def ruolo: Rep[Int] = column[Int]("Ruolo")
    override def * : ProvenShape[Persona] = (nome, cognome,dataNascita,numTelefono,ruolo,terminaleId.?,id.?).mapTo[Persona]
    def terminale: ForeignKeyQuery[TerminaleTableRep, Terminale] = foreignKey("Terminale_IdTerminale", terminaleId, TableQuery[TerminaleTableRep])(_.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.SetNull)
  }
}
