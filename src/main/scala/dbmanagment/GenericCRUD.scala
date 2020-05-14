package dbmanagment
import slick.jdbc.SQLServerProfile.api._
import slick.lifted.{AbstractTable, TableQuery}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.reflect.runtime.{universe => runtime}
object Reflection {
  val runtimeMirror: runtime.Mirror = runtime.runtimeMirror(getClass.getClassLoader)
  def getTypeTag[C: runtime.TypeTag]: runtime.TypeTag[C] = runtime.typeTag[C]
  def createClassByConstructor[C: runtime.TypeTag](args: Any*): C =
    runtimeMirror.reflectClass(getTypeTag[C].tpe.typeSymbol.asClass)
      .reflectConstructor(runtime.typeOf[C].decl(runtime.termNames.CONSTRUCTOR)
        .asMethod)(args: _*).asInstanceOf[C]
}
abstract class GenericTable[T](tag: Tag, name: String,nameId:String) extends Table[T](tag, name) {
  def id = column[Int](nameId, O.PrimaryKey, O.AutoInc)
}
abstract class GenericTableQuery[T, C <: AbstractTable[T]: runtime.TypeTag] {
  import Reflection._
  // look at following code: Students, if you want to initialize Students
  // you're gonna need a tag parameter, that's why we pass tag here
  val table = TableQuery.apply(tag => createClassByConstructor[C](tag))
}
sealed trait GenericCRUD[T,C <: GenericTable[T]] extends GenericTableQuery[T,C] with DB[C,T] {
  val queryById = Compiled((id: Rep[Int]) => table.filter(_.id === id))
  val queryByIdPlus =  (id: Seq[Int]) => table.filter(_.id.inSet(id))
  def select(id:Int): Future[Option[T]]
  def selectAll: Future[List[T]]
  def insert(c: T): Future[Int]
  def insertAll(c: List[T]): Future[List[Int]]
  def delete(id:Int): Future[Int]
  def deleteAll(id:List[Int]): Future[Int]
  def update(c: T): Future[Int]
  def selectFilter(f:C=>Rep[Boolean]): Future[List[T]]
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
  }
}