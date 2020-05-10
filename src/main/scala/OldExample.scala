package dbmanagment
import dbmanagment.GiornoTable.GiornoTableRep
import slick.jdbc.SQLServerProfile.api._
import slick.lifted.{AbstractTable, TableQuery}
import scala.reflect.runtime.{universe => ru}
/*
object ReflectionOld {
  val runtimeMirror = ru.runtimeMirror(getClass.getClassLoader)
  def getTypeTag[C: ru.TypeTag] = ru.typeTag[C]
  def createClassByConstructor[C: ru.TypeTag](args: Any*) =
    runtimeMirror.reflectClass(getTypeTag[C].tpe.typeSymbol.asClass)
      .reflectConstructor(ru.typeOf[C].decl(ru.termNames.CONSTRUCTOR)
        .asMethod)(args: _*).asInstanceOf[C]
}
abstract class GenericTableQueryOld[T, C <: AbstractTable[T]: ru.TypeTag] {
  import Reflection._
  // look at following code: Students, if you want to initialize Students
  // you're gonna need a tag parameter, that's why we pass tag here
  val table = TableQuery.apply(tag => createClassByConstructor[C](tag))
}
abstract class GenericTableOld[T](tag: Tag, name: String,nameId:String) extends Table[T](tag, name) {
  def id = column[Int](nameId, O.PrimaryKey, O.AutoInc)
}
trait GenericCRUDOld[T,C <: GenericTableOld[T]] extends GenericTableQueryOld[T,C] with DB[C,T] {
  private val queryById = Compiled((id: Rep[Int]) => table.filter(_.id === id))
  def create(c: T) = {table += c;run(table.result)}
  def read(id:Int) = run(queryById(id).result.headOption)
  def delete(id:Int) = run(queryById(id).delete)
  def update(id:Int,c: T)=run(queryById(id).update(c))

}
object implicitsGenericOld{
  case class BrandsZonaOld[T,C<: GenericTableOld[T]:ru.TypeTag]() extends GenericCRUDOld[T,C] {
    def create(elem: T)(implicit ordering: GenericCRUDOld[T,C]) = super.create(elem)

    def read[T,C <: GenericTableOld[T]](id: Int)(implicit ordering: GenericCRUDOld[T,C]) = super.read(id)

    def delete[T,C <: GenericTableOld[T]](id: Int)(implicit ordering: GenericCRUDOld[T,C]) = super.delete(id)

    def update(id: Int, elem: T)(implicit ordering: GenericCRUDOld[T,C]) = super.update(id, elem)
  }
}
object implicitsGenericOld2 {
  object BrandsGiornoOld extends GenericCRUDOld[Giorno,GiornoTableRep] {
    def create[T,C <: GenericTableOld[T]](elem: GiornoTableRep#TableElementType)(implicit ordering: GenericCRUDOld[T,C]) = super.create(elem)

    def read[T,C <: GenericTableOld[T]](id:Int)(implicit ordering: GenericCRUDOld[T,C])= super.read(id)

    def delete[T,C <: GenericTableOld[T]](id:Int)(implicit ordering: GenericCRUDOld[T,C])= super.delete(id)

    def update[T,C <: GenericTableOld[T]](id:Int,elem: GiornoTableRep#TableElementType)(implicit ordering: GenericCRUDOld[T,C])= super.update(id,elem)
  }
}
*/