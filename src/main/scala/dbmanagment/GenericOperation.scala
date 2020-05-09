package dbmanagment
import dbmanagment.GiornoTable.{Giorno, GiornoTableRep}
import dbmanagment.ZonaTable.{Zona, ZonaTableRep}
import slick.jdbc.SQLServerProfile.api._
abstract class GenericTable[T](tag: Tag, name: String,nameId:String) extends Table[T](tag, name) {
  def id = column[Int](nameId, O.PrimaryKey, O.AutoInc)
}
trait GenericCRUD[C <: GenericTable[T], T] extends DB[C,T] {
  private val queryById = Compiled((id: Rep[Int]) => table.filter(_.id === id))
  def create(c: C#TableElementType) = {table += c;run(table.result)}
  def read(id:Int) = run(queryById(id).result.headOption)
  def delete(id:Int) = run(queryById(id).delete)
  def update(id:Int,c: C#TableElementType)=run(queryById(id).update(c))
}
object implicitsGeneric {

  object BrandsZona extends GenericCRUD[ZonaTableRep, Zona] {
    override val table = TableQuery[ZonaTableRep]

    def create[A <: GenericTable[T], T](elem: ZonaTableRep#TableElementType)(implicit ordering: GenericCRUD[A, T]) = super.create(elem)

    def read[A <: GenericTable[T], T](id: Int)(implicit ordering: GenericCRUD[A, T]) = super.read(id)

    def delete[A <: GenericTable[T], T](id: Int)(implicit ordering: GenericCRUD[A, T]) = super.delete(id)

    def update[A <: GenericTable[T], T](id: Int, elem: ZonaTableRep#TableElementType)(implicit ordering: GenericCRUD[A, T]) = super.update(id, elem)
  }

}
object implicitsGeneric2 {
  object BrandsGiorno extends GenericCRUD[GiornoTableRep,Giorno] {
    override val table = TableQuery[GiornoTableRep]
    def create[A <: GenericTable[T],T](elem: GiornoTableRep#TableElementType)(implicit ordering: GenericCRUD[A,T])= super.create(elem)
    def read[A <: GenericTable[T],T](id:Int)(implicit ordering: GenericCRUD[A,T])= super.read(id)
    def delete[A <: GenericTable[T],T](id:Int)(implicit ordering: GenericCRUD[A,T])= super.delete(id)
    def update[A <: GenericTable[T],T](id:Int,elem: GiornoTableRep#TableElementType)(implicit ordering: GenericCRUD[A,T])= super.update(id,elem)
  }
}
