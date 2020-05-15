package dbmanagment.operation

import dbmanagment.setting.implicitsGeneric.Brands
import dbmanagment.table.ContrattoTable.ContrattoTableRep
import dbmanagment.table.GiornoInSettimanaTable.GiornoInSettimanaTableRep
import dbmanagment.table.GiornoTable.GiornoTableRep
import dbmanagment.table.GruppoTerminaleTable.GruppoTerminaleTableRep
import dbmanagment.table.ParametroTable.ParametroTableRep
import dbmanagment.table.PersonaTable.PersonaTableRep
import dbmanagment.table.PresenzaTable.PresenzaTableRep
import dbmanagment.table.RichiestaTable.RichiestaTableRep
import dbmanagment.table.RichiestaTeoricaTable.RichiestaTeoricaTableRep
import dbmanagment.table.RisultatoTable.RisultatoTableRep
import dbmanagment.table.SettimanaTable.SettimanaTableRep
import dbmanagment.table.StoricoContrattoTable.StoricoContrattoTableRep
import dbmanagment.table.StraordinarioTable.StraordinarioTableRep
import dbmanagment.table.TerminaleTable.TerminaleTableRep
import dbmanagment.table.TurnoTable.TurnoTableRep
import dbmanagment.table.ZonaTable.ZonaTableRep
import utils.caseclass.CaseClassDB._

import scala.concurrent.Future
trait Crud[A]{
  def select(element:Int):Future[Option[A]]
  def selectAll: Future[List[A]]
  def insert(element:A):Future[Int]
  def insertAll(element:List[A]):Future[List[Int]]
  def delete(element:A):Future[Int]
  def deleteAll(element:List[A]): Future[Int]
  def update(element:A):Future[Int]
}
object ImplicitCrudG {
  implicit object CrudContratto extends Crud[Contratto] {
    protected def SelectType: Brands[Contratto, ContrattoTableRep] =Brands[Contratto,ContrattoTableRep]()
    override def insert(element: Contratto):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[Contratto]] = SelectType.select(element)
    override def delete(element: Contratto): Future[Int] = SelectType.delete(element.idContratto.get)
    override def update(element: Contratto): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Contratto]] = SelectType.selectAll
    override def insertAll(element: List[Contratto]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Contratto]): Future[Int] = SelectType.deleteAll(element.map(t=>t.idContratto.get))
  }
  implicit object CrudGiornoInSettimana extends Crud[GiornoInSettimana] {
    protected def SelectType: Brands[GiornoInSettimana, GiornoInSettimanaTableRep] =Brands[GiornoInSettimana,GiornoInSettimanaTableRep]()
    override def insert(element: GiornoInSettimana):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[GiornoInSettimana]] = SelectType.select(element)
    override def delete(element: GiornoInSettimana): Future[Int] = SelectType.delete(element.idSettimana.get)
    override def update(element: GiornoInSettimana): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[GiornoInSettimana]] = SelectType.selectAll
    override def insertAll(element: List[GiornoInSettimana]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[GiornoInSettimana]): Future[Int] = SelectType.deleteAll(element.map(t=>t.idSettimana.get))
  }
  implicit object CrudGiorno extends Crud[Giorno] {
    protected def SelectType: Brands[Giorno, GiornoTableRep] =Brands[Giorno,GiornoTableRep]()
    override def insert(element: Giorno):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[Giorno]] = SelectType.select(element)
    override def delete(element: Giorno): Future[Int] = SelectType.delete(element.idGiorno.get)
    override def update(element: Giorno): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Giorno]] = SelectType.selectAll
    override def insertAll(element: List[Giorno]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Giorno]): Future[Int] = SelectType.deleteAll(element.map(t=>t.idGiorno.get))
  }
  implicit object CrudGruppoTerminale extends Crud[GruppoTerminale] {
    protected def SelectType: Brands[GruppoTerminale, GruppoTerminaleTableRep] =Brands[GruppoTerminale,GruppoTerminaleTableRep]()
    override def insert(element: GruppoTerminale):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[GruppoTerminale]] = SelectType.select(element)
    override def delete(element: GruppoTerminale): Future[Int] = SelectType.delete(element.idGruppoTerminale.get)
    override def update(element: GruppoTerminale): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[GruppoTerminale]] = SelectType.selectAll
    override def insertAll(element: List[GruppoTerminale]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[GruppoTerminale]): Future[Int] = SelectType.deleteAll(element.map(t=>t.idGruppoTerminale.get))
  }
  implicit object CrudParametro extends Crud[Parametro] {
    protected def SelectType: Brands[Parametro, ParametroTableRep] =Brands[Parametro,ParametroTableRep]()
    override def insert(element: Parametro):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[Parametro]] = SelectType.select(element)
    override def delete(element: Parametro): Future[Int] = SelectType.delete(element.idParametri.get)
    override def update(element: Parametro): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Parametro]] = SelectType.selectAll
    override def insertAll(element: List[Parametro]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Parametro]): Future[Int] = SelectType.deleteAll(element.map(t=>t.idParametri.get))
  }
  implicit object CrudPersona extends Crud[Persona] {
    protected def SelectType: Brands[Persona, PersonaTableRep] =Brands[Persona,PersonaTableRep]()
    override def insert(element: Persona):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[Persona]] = SelectType.select(element)
    override def delete(element: Persona): Future[Int] = SelectType.delete(element.matricola.get)
    override def update(element: Persona): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Persona]] = SelectType.selectAll
    override def insertAll(element: List[Persona]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Persona]): Future[Int] = SelectType.deleteAll(element.map(t=>t.matricola.get))
  }
  implicit object CrudPresenza extends Crud[Presenza] {
    protected def SelectType: Brands[Presenza, PresenzaTableRep] =Brands[Presenza,PresenzaTableRep]()
    override def insert(element: Presenza):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[Presenza]] = SelectType.select(element)
    override def delete(element: Presenza): Future[Int] = SelectType.delete(element.idPresenza.get)
    override def update(element: Presenza): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Presenza]] = SelectType.selectAll
    override def insertAll(element: List[Presenza]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Presenza]): Future[Int] = SelectType.deleteAll(element.map(t=>t.idPresenza.get))
  }
  implicit object CrudRichiesta extends Crud[Richiesta] {
    protected def SelectType: Brands[Richiesta, RichiestaTableRep] =Brands[Richiesta,RichiestaTableRep]()
    override def insert(element: Richiesta):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[Richiesta]] = SelectType.select(element)
    override def delete(element: Richiesta): Future[Int] = SelectType.delete(element.idRichiesta.get)
    override def update(element: Richiesta): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Richiesta]] = SelectType.selectAll
    override def insertAll(element: List[Richiesta]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Richiesta]): Future[Int] = SelectType.deleteAll(element.map(t=>t.idRichiesta.get))
  }
  implicit object CrudRichiestaTeorica extends Crud[RichiestaTeorica] {
    protected def SelectType: Brands[RichiestaTeorica, RichiestaTeoricaTableRep] =Brands[RichiestaTeorica,RichiestaTeoricaTableRep]()
    override def insert(element: RichiestaTeorica):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[RichiestaTeorica]] = SelectType.select(element)
    override def delete(element: RichiestaTeorica): Future[Int] = SelectType.delete(element.idRichiestaTeorica.get)
    override def update(element: RichiestaTeorica): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[RichiestaTeorica]] = SelectType.selectAll
    override def insertAll(element: List[RichiestaTeorica]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[RichiestaTeorica]): Future[Int] = SelectType.deleteAll(element.map(t=>t.idRichiestaTeorica.get))
  }
  implicit object CrudRisultato extends Crud[Risultato] {
    protected def SelectType: Brands[Risultato, RisultatoTableRep] =Brands[Risultato,RisultatoTableRep]()
    override def insert(element: Risultato):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[Risultato]] = SelectType.select(element)
    override def delete(element: Risultato): Future[Int] = SelectType.delete(element.idRisultato.get)
    override def update(element: Risultato): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Risultato]] = SelectType.selectAll
    override def insertAll(element: List[Risultato]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Risultato]): Future[Int] = SelectType.deleteAll(element.map(t=>t.idRisultato.get))
  }
  implicit object CrudSettimana extends Crud[Settimana] {
    protected def SelectType: Brands[Settimana, SettimanaTableRep] =Brands[Settimana,SettimanaTableRep]()
    override def insert(element: Settimana):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[Settimana]] = SelectType.select(element)
    override def delete(element: Settimana): Future[Int] = SelectType.delete(element.idZone.get)
    override def update(element: Settimana): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Settimana]] = SelectType.selectAll
    override def insertAll(element: List[Settimana]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Settimana]): Future[Int] = SelectType.deleteAll(element.map(t=>t.idZone.get))
  }
  implicit object CrudStoricoContratto extends Crud[StoricoContratto] {
    protected def SelectType: Brands[StoricoContratto, StoricoContrattoTableRep] =Brands[StoricoContratto,StoricoContrattoTableRep]()
    override def insert(element: StoricoContratto):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[StoricoContratto]] = SelectType.select(element)
    override def delete(element: StoricoContratto): Future[Int] = SelectType.delete(element.idStoricoContratto.get)
    override def update(element: StoricoContratto): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[StoricoContratto]] = SelectType.selectAll
    override def insertAll(element: List[StoricoContratto]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[StoricoContratto]): Future[Int] = SelectType.deleteAll(element.map(t=>t.idStoricoContratto.get))
  }
  implicit object CrudStraordinario extends Crud[Straordinario] {
    protected def SelectType: Brands[Straordinario, StraordinarioTableRep] =Brands[Straordinario,StraordinarioTableRep]()
    override def insert(element: Straordinario):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[Straordinario]] = SelectType.select(element)
    override def delete(element: Straordinario): Future[Int] = SelectType.delete(element.idStraordinario.get)
    override def update(element: Straordinario): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Straordinario]] = SelectType.selectAll
    override def insertAll(element: List[Straordinario]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Straordinario]): Future[Int] = SelectType.deleteAll(element.map(t=>t.idStraordinario.get))
  }
  implicit object CrudTerminale extends Crud[Terminale] {
    protected def SelectType: Brands[Terminale, TerminaleTableRep] =Brands[Terminale,TerminaleTableRep]()
    override def insert(element: Terminale):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[Terminale]] = SelectType.select(element)
    override def delete(element: Terminale): Future[Int] = SelectType.delete(element.idTerminale.get)
    override def update(element: Terminale): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Terminale]] = SelectType.selectAll
    override def insertAll(element: List[Terminale]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Terminale]): Future[Int] = SelectType.deleteAll(element.map(t=>t.idTerminale.get))
  }
  implicit object CrudTurno extends Crud[Turno] {
    protected def SelectType: Brands[Turno, TurnoTableRep] =Brands[Turno,TurnoTableRep]()
    override def insert(element: Turno):Future[Int]  = SelectType.insert(element)
    override def select(element: Int): Future[Option[Turno]] = SelectType.select(element)
    override def delete(element: Turno): Future[Int] = SelectType.delete(element.id.get)
    override def update(element: Turno): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Turno]] = SelectType.selectAll
    override def insertAll(element: List[Turno]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Turno]): Future[Int] = SelectType.deleteAll(element.map(t=>t.id.get))
  }
  implicit object CrudZona extends Crud[Zona] {
    protected def SelectType: Brands[Zona, ZonaTableRep] =Brands[Zona,ZonaTableRep]()
    override def insert(element: Zona):Future[Int] = SelectType.insert(element)
    override def select(element: Int): Future[Option[Zona]] = SelectType.select(element)
    override def delete(element: Zona): Future[Int] = SelectType.delete(element.idZone.get)
    override def update(element: Zona): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Zona]] = SelectType.selectAll
    override def insertAll(element: List[Zona]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Zona]): Future[Int] = SelectType.deleteAll(element.map(t=>t.idZone.get))

  }


}
