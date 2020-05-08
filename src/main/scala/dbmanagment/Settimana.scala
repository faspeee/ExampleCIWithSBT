package dbmanagment

import java.time.LocalDateTime

import dbmanagment.DBConnection.db
import dbmanagment.Settimana.SettimanaSet
import dbmanagment.ZonaTable.zone
import slick.dbio.DBIO
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.ClassTag
/**
 * Manage the Turno table of the db.
 */



trait GenericCRUD[C <: GenericTable[T], T]{
  protected val table: TableQuery[C]
  def all: Future[Seq[C#TableElementType]] = db.run(table.result)
  def create(c: C#TableElementType): Future[Int] = db.run(table += c)
  def join(c: TableQuery[C],d: TableQuery[C])= for(x<-c;y<-d)yield(x,y)
  def join2(c: TableQuery[C]*)= c.foreach(t=>t)
  def esegui(c:C#TableElementType)=db.run(table.result)
}
abstract class GenericTable[T](tag: Tag, name: String) extends Table[T](tag, name) {
}
object Settimana{

  case class SettimanaSet(Quantita:Int,NomeGiorno:String,IdGiorno:Option[Int]=None)
  class SettimanaTableRep(tag: Tag) extends GenericTable[SettimanaSet](tag, "GiornoSets") {
    def id = column[Int]("IdGiorno", O.PrimaryKey,O.AutoInc) // This is the primary key column
    def quantita = column[Int]("Quantita")
    def nomeGiorno = column[String]("NomeGiorno")
    // Every table needs a * projection with the same type as the table's type parameter
    def * =  ( quantita,nomeGiorno,id.?) <>((SettimanaSet.apply _).tupled, SettimanaSet.unapply)
  }
  object Brands extends GenericCRUD[ SettimanaTableRep,SettimanaSet] {
    override val table = TableQuery[SettimanaTableRep]
    def create[A <: GenericTable[T],T](elem: SettimanaTableRep#TableElementType)(implicit ordering: GenericCRUD[A,T]): Future[Int] = super.create(elem)

  }
}
object TrySettimana extends App{
  import Settimana._
  import Brands._
  val s = SettimanaSet(1,"")
  val myStringOrdering = Brands
  implicit val implicitStringOrdering: GenericCRUD[SettimanaTableRep,SettimanaSet] = myStringOrdering
  println(create(s))
}
