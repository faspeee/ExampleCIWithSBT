package dbmanagment

import dbmanagment.CaseClassDB._
import dbmanagment.GiornoTable.GiornoTableRep
import dbmanagment.ZonaTable.ZonaTableRep
import dbmanagment.TurnoTable.TurnoTableRep
import dbmanagment.implicitsGeneric.Brands
import scala.reflect.runtime.{universe => runtime}
trait GenericCrudClass[F]{
  def create(element:F)
  def read(element:F)
  def update(element:F)
  def delete(element:F)
}
object GenericCrudClass{
  case class tableType[T,C<:GenericTable[T]:runtime.TypeTag](){
    def tableType: GenericCRUD[T, C] ={
      val implicits:GenericCRUD[T,C] = Brands[T,C]()
      implicits
    }
  }
  //scala reflect
  def selectTableType[F](caseClass:F) = caseClass match {
    case Giorno(_,_,_) =>tableType[Giorno,GiornoTableRep]().tableType.create(caseClass.asInstanceOf[Giorno])// esempipo  seguire
    case Zona =>tableType[Zona,ZonaTableRep]().tableType
    case Turno=>tableType[Turno,TurnoTableRep]().tableType
  }
  def create[F](element:F)=element match {
    case Giorno(_,_,_)=>selectTableType(element)//.create(element.asInstanceOf)
    case Zona(_,_)=>selectTableType(element)//.create(element.asInstanceOf)
    case Turno(_,_,_)=>selectTableType(element)//.create(element.asInstanceOf)
  }
  def read[F](element:F)=element match {
    case Giorno(_,_,_)=>
    case Zona(_,_)=>
    case Turno(_,_,_)=>
  }
  def update[F](element:F)=element match {
    case Giorno(_,_,_)=>
    case Zona(_,_)=>
    case Turno(_,_,_)=>
  }
  def delete[F](element:F)=element match {
    case Giorno(_,_,_)=>
    case Zona(_,_)=>
    case Turno(_,_,_)=>
  }
}
object tryobject extends App{
  import GenericCrudClass._
  val s = Giorno(1,"2")
  create(s)
}