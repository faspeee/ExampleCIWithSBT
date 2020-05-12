package dbmanagment

import dbmanagment.CaseClassDB._
import dbmanagment.GiornoTable.GiornoTableRep
import dbmanagment.ZonaTable.ZonaTableRep
import dbmanagment.TurnoTable.TurnoTableRep
import dbmanagment.implicitsGeneric.Brands

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.runtime.{universe => runtime}
import scala.util.{Failure, Success}
trait GenericCrudClass{

  def insert[F](element:F): Future[Int]
  def selectAll[F](element:F): Future[List[Any]]
  def select[F](element:F):Future[Option[Any]]
  def update[F](element:F):Future[Int]
  def delete[F](element:F):Future[Int]
}
object GenericCrudClass extends GenericCrudClass{
  def apply(): GenericCrudClass =  GenericCrudClass
  //scala reflect tableType.create(caseClass.asInstanceOf[Giorno])
  //implicit val implicitStringOrdering2: GenericCRUD[Giorno,GiornoTableRep] = Brands[ Giorno,GiornoTableRep]()
  def selectTableType[F,C<:GenericTable[F]:runtime.TypeTag]() =  {
    Brands[F,C]()
  }
  override def insert[F](element:F) =element match {
    case Giorno(x,y,z)=>selectTableType[Giorno,GiornoTableRep]().insert(Giorno(x,y,z))//.create(element.asInstanceOf)
    case Zona(x,y)=>selectTableType[Zona,ZonaTableRep]().insert(Zona(x,y))//.create(element)//.create(element.asInstanceOf)
    case Turno(x,y,z)=>selectTableType[Turno,TurnoTableRep]().insert(Turno(x,y,z))//.create(element)//.create(element.asInstanceOf)
  }
  override def select[F](element:F)=element match {
    case Giorno(_,_,z)=>selectTableType[Giorno,GiornoTableRep]().select(z.head)//.create(element.asInstanceOf)
    case Zona(_,z)=>selectTableType[Zona,ZonaTableRep]().select(z.head)//.create(element)//.create(element.asInstanceOf)
    case Turno(_,_,z)=>selectTableType[Turno,TurnoTableRep]().select(z.head)//.create(element)//.create(element.asInstanceOf)
  }
  override def update[F](element:F)=element match {
    case Giorno(x,y,z)=>selectTableType[Giorno,GiornoTableRep]().update(Giorno(x,y,z))//.create(element.asInstanceOf)
    case Zona(x,y)=>selectTableType[Zona,ZonaTableRep]().update(Zona(x,y))//.create(element)//.create(element.asInstanceOf)
    case Turno(x,y,z)=>selectTableType[Turno,TurnoTableRep]().update(Turno(x,y,z))//.create(element)//.create(element.asInstanceOf)
  }
  override def delete[F](element:F)=element match {
    case Giorno(_,_,z)=>selectTableType[Giorno,GiornoTableRep]().delete(z.head)//.create(element.asInstanceOf)
    case Zona(_,z)=>selectTableType[Zona,ZonaTableRep]().delete(z.head)//.create(element)//.create(element.asInstanceOf)
    case Turno(_,_,z)=>selectTableType[Turno,TurnoTableRep]().delete(z.head)//.create(element)//.create(element.asInstanceOf)
  }

  override def selectAll[F](element: F)=element match {
    case Giorno=>selectTableType[Giorno,GiornoTableRep]().selectAll//.create(element.asInstanceOf)
    case Zona=>selectTableType[Zona,ZonaTableRep]().selectAll//.create(element)//.create(element.asInstanceOf)
    case Turno=>selectTableType[Turno,TurnoTableRep]().selectAll//.create(element)//.create(element.asInstanceOf)
  }
}
object tryAll extends App{
    import GenericCrudClass._
    val s = Zona
    selectAll(s)  onComplete { posts =>
      for (post <- posts) println(post)
    }
   while(true){}
}