package dbmanagment.table
import dbmanagment.setting.GenericTable
import slick.jdbc.SQLServerProfile.api._
import slick.lifted.ProvenShape
import utils.caseclass.CaseClassDB.Contratto
object ContrattoTable {
  class ContrattoTableRep(tag: Tag) extends GenericTable[Contratto](tag, "ContratoeSets","IdContratto") {
    def tipoContratto: Rep[String] = column[String]("Parametri_IdParametri")
    def turnoFisso: Rep[Byte] = column[Byte]("SettimaneIdSettimane")
    override def * : ProvenShape[Contratto] = (tipoContratto,turnoFisso,id.?).mapTo[Contratto]

  }
}
