package dbmanagment.setting
import slick.jdbc.SQLServerProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.reflect.runtime.{universe => runtime}

sealed trait GenericCRUD[T,C <: GenericTable[T]] extends GenericTableQuery[T,C] with DB[C,T] {
  val queryById = Compiled((id: Rep[Int]) => table.filter(_.id === id))
  val queryByIdPlus: Seq[Int] => Query[C, T, Seq] = (id: Seq[Int]) => table.filter(_.id.inSet(id))
  def select(id:Int): Future[Option[T]]
  def selectAll: Future[List[T]]
  def insert(c: T): Future[Int]
  def insertAll(c: List[T]): Future[List[Int]]
  def delete(id:Int): Future[Int]
  def deleteAll(id:List[Int]): Future[Int]
  def update(c: T): Future[Int]
  def selectFilter(f:C=>Rep[Boolean]): Future[List[T]]
  def execJoin[A,B](f:Query[A,B,Seq]): Future[List[B]]
}
object implicitsGeneric{
  case class Brands[T,C<: GenericTable[T]:runtime.TypeTag]() extends GenericCRUD[T,C] {
    override def selectAll: Future[List[T]] = super.run(table.result).map(result=>result.toList)
    override def select(id: Int): Future[Option[T]] = super.run(queryById(id).result.headOption)
    override def insert(elem: T): Future[Int]= super.run((table returning table.map(_.id)) += elem)
    override def insertAll(c: List[T]): Future[List[Int]] =  super.run((table returning table.map(_.id)) ++= c).map(result=>result.toList)
    override def delete(id: Int): Future[Int] = super.run(queryById(id).delete)
    override def deleteAll(id: List[Int]): Future[Int] = super. run(queryByIdPlus(id).delete)
    override def update(elem: T): Future[Int] = super.run(table.insertOrUpdate(elem))
    override def selectFilter(f:C=>Rep[Boolean]): Future[List[T]] = super.run(table.withFilter(f).result).map(result=>result.toList)
    override def execJoin[A,B](f:Query[A,B,Seq]): Future[List[B]] = super.run(f.result).map(result=>result.toList)
  }
}