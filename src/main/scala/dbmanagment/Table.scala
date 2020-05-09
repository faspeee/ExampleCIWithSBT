package dbmanagment
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
abstract class GenericTable2[T](tag: Tag, name: String,nameId:String) extends Table[T](tag, name) {
  def id = column[Int](nameId, O.PrimaryKey, O.AutoInc)
}
trait GenericCRUD2[C <: GenericTable[T], T] extends DB[C,T] {
  private val queryById = Compiled((id: Rep[Int]) => table.filter(_.id === id))
  def create(c: C#TableElementType) = {table += c;run(table.result)}
  def read(id:Int) = run(queryById(id).result.headOption)
  def delete(id:Int) = run(queryById(id).delete)
  def update(id:Int,c: C#TableElementType)=run(queryById(id).update(c))
}
sealed trait Operation[F]{
  def creates[A](element:F):Future[Seq[F]]
  def read(element:F):Option[F]
  def update(element:F):Option[F]
  def delete(element:F):Option[F]
}
object Operation{
  implicit class RichOperation[F:Operation](t:F){
    def creates():Future[Seq[F]]  =implicitly[Operation[F]].creates(t)
    def read():Option[F]=implicitly[Operation[F]].read(t)
    def update():Option[F]=implicitly[Operation[F]].update(t)
    def delete():Option[F]=implicitly[Operation[F]].delete(t)
  }
}
object implicits {
  import ZonaTable._
  implicit object Insert extends Operation[Zona] {

    object Brands extends GenericCRUD2[ZonaTableRep,Zona] {
      override val table = TableQuery[ZonaTableRep]
      def create[A <: GenericTable[T],T](elem: ZonaTableRep#TableElementType)(implicit ordering: GenericCRUD2[A,T])= super.create(elem)
      def read[A <: GenericTable[T],T](id:Int)(implicit ordering: GenericCRUD2[A,T])= super.read(id)
      def delete[A <: GenericTable[T],T](id:Int)(implicit ordering: GenericCRUD2[A,T])= super.delete(id)
      def update[A <: GenericTable[T],T](id:Int,elem: ZonaTableRep#TableElementType)(implicit ordering: GenericCRUD2[A,T])= super.update(id,elem)
    }
    import Brands._
    val myStringOrdering = Brands
    implicit val implicitStringOrdering: GenericCRUD2[ZonaTableRep,Zona] = myStringOrdering
    override def creates[A](element: Zona):Future[Seq[Zona]] = create(element)
    override def read(element: Zona): Option[Zona] = read(element)
    override def update(element: Zona): Option[Zona] = ???
    override def delete(element: Zona): Option[Zona] = delete(element)
  }

}
object tryCase extends App{
  import Operation._,implicits._
  import ZonaTable._
  val s = Zona("")
  s.read()
}