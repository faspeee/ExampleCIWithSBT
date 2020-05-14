package dbmanagment
import dbmanagment.CaseClassDB.{Giorno, Persona, Terminale, Turno, Zona}
import dbmanagment.GiornoTable.GiornoTableRep
import dbmanagment.PersonaTable.PersonaTableRep
import dbmanagment.TerminaleTable.TerminaleTableRep
import dbmanagment.TurnoTable.TurnoTableRep
import dbmanagment.ZonaTable.ZonaTableRep
import dbmanagment.implicitsGeneric.Brands
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
  implicit object CrudZona extends Crud[Zona] {
    protected def SelectType: Brands[Zona, ZonaTableRep] =Brands[Zona,ZonaTableRep]()
    override def insert(element: Zona):Future[Int] = SelectType.insert(element)
    override def select(element: Int): Future[Option[Zona]] = SelectType.select(element)
    override def delete(element: Zona): Future[Int] = SelectType.delete(element.IdZone.get)
    override def update(element: Zona): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Zona]] = SelectType.selectAll
    override def insertAll(element: List[Zona]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Zona]): Future[Int] = SelectType.deleteAll(element.map(t=>t.IdZone.get))

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
  // a generic way of giving implicit values
  implicit object CrudGiorno extends Crud[Giorno] {
    protected def SelectType: Brands[Giorno, GiornoTableRep] =Brands[Giorno,GiornoTableRep]()
    override def insert(element: Giorno):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[Giorno]] = SelectType.select(element)
    override def delete(element: Giorno): Future[Int] = SelectType.delete(element.IdGiorno.get)
    override def update(element: Giorno): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Giorno]] = SelectType.selectAll
    override def insertAll(element: List[Giorno]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Giorno]): Future[Int] = SelectType.deleteAll(element.map(t=>t.IdGiorno.get))
  }
   implicit object CrudPersona extends Crud[Persona] {
    protected def SelectType: Brands[Persona, PersonaTableRep] =Brands[Persona,PersonaTableRep]()
    override def insert(element: Persona):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[Persona]] = SelectType.select(element)
    override def delete(element: Persona): Future[Int] = SelectType.delete(element.Matricola.get)
    override def update(element: Persona): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Persona]] = SelectType.selectAll
    override def insertAll(element: List[Persona]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Persona]): Future[Int] = SelectType.deleteAll(element.map(t=>t.Matricola.get))
  }
  implicit object CrudTerminale extends Crud[Terminale] {
    protected def SelectType: Brands[Terminale, TerminaleTableRep] =Brands[Terminale,TerminaleTableRep]()
    override def insert(element: Terminale):Future[Int]  =  SelectType.insert(element)
    override def select(element: Int): Future[Option[Terminale]] = SelectType.select(element)
    override def delete(element: Terminale): Future[Int] = SelectType.delete(element.IdTerminale.get)
    override def update(element: Terminale): Future[Int] = SelectType.update(element)
    override def selectAll: Future[List[Terminale]] = SelectType.selectAll
    override def insertAll(element: List[Terminale]): Future[List[Int]] = SelectType.insertAll(element)
    override def deleteAll(element: List[Terminale]): Future[Int] = SelectType.deleteAll(element.map(t=>t.IdTerminale.get))
  }
}
