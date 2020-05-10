package dbmanagment

import dbmanagment.CaseClassDB.Persona
import slick.lifted.Tag

object PersonaTable {
    class PersonaTableRep(tag:Tag) extends GenericTable[Persona](tag,"PersonaSets","Matricola") {
      override def * = ???
    }
}
