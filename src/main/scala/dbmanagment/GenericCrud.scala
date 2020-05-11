package dbmanagment

import dbmanagment.CaseClassDB._
import dbmanagment.GiornoTable.GiornoTableRep
import dbmanagment.ZonaTable.ZonaTableRep
import dbmanagment.TurnoTable.TurnoTableRep
import dbmanagment.implicitsGeneric.Brands
import slick.jdbc.SQLServerProfile.api._
import slick.lifted.AbstractTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.runtime.{universe => runtime}
import scala.util.{Failure, Success}
trait GenericCrudClass{
  def create[F](element:F): Future[Int]
  def read[F](element:F):Future[Option[Any]]
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
  override def create[F](element:F) =element match {
    case Giorno(x,y,z)=>selectTableType[Giorno,GiornoTableRep]().create(Giorno(x,y,z))//.create(element.asInstanceOf)
    case Zona(x,y)=>selectTableType[Zona,ZonaTableRep]().create(Zona(x,y))//.create(element)//.create(element.asInstanceOf)
    case Turno(x,y,z)=>selectTableType[Turno,TurnoTableRep]().create(Turno(x,y,z))//.create(element)//.create(element.asInstanceOf)
  }
  override def read[F](element:F)=element match {
    case Giorno(_,_,z)=>selectTableType[Giorno,GiornoTableRep]().read(z.head)//.create(element.asInstanceOf)
    case Zona(_,z)=>selectTableType[Zona,ZonaTableRep]().read(z.head)//.create(element)//.create(element.asInstanceOf)
    case Turno(_,_,z)=>selectTableType[Turno,TurnoTableRep]().read(z.head)//.create(element)//.create(element.asInstanceOf)
  }
  override def update[F](element:F)=element match {
    case Giorno(x,y,z)=>selectTableType[Giorno,GiornoTableRep]().update(z.head,Giorno(x,y,z))//.create(element.asInstanceOf)
    case Zona(x,y)=>selectTableType[Zona,ZonaTableRep]().update(y.head,Zona(x,y))//.create(element)//.create(element.asInstanceOf)
    case Turno(x,y,z)=>selectTableType[Turno,TurnoTableRep]().update(z.head,Turno(x,y,z))//.create(element)//.create(element.asInstanceOf)
  }
  override def delete[F](element:F)=element match {
    case Giorno(_,_,z)=>selectTableType[Giorno,GiornoTableRep]().delete(z.head)//.create(element.asInstanceOf)
    case Zona(_,z)=>selectTableType[Zona,ZonaTableRep]().delete(z.head)//.create(element)//.create(element.asInstanceOf)
    case Turno(_,_,z)=>selectTableType[Turno,TurnoTableRep]().delete(z.head)//.create(element)//.create(element.asInstanceOf)
  }
}
object tryobject extends App{

  val r:GenericCrudClass = GenericCrudClass()
  val s = Seq(Turno("22","Juanito"),Turno("22","Juanito"),Turno("22","Juanito"))
  val ss = r.create(s)
    ss.onComplete{
      case Success(x) => println("inserite ", x)
      case Failure(x) => println("DIO",x)
    }
  while(true){}
}
