package dbmanagment
import dbmanagment.CaseClassDB.Persona
import dbmanagment.ImplicitCrudG.CrudPersona
import dbmanagment.PersonaTable.PersonaTableRep
import dbmanagment.implicitsGeneric.Brands
import slick.lifted.Rep
import slick.jdbc.SQLServerProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
trait PersonaOperation extends OperationCrud[Persona]{
  def filterByName(name:String):Future[Seq[Persona]]
  def filterBySurname(surname: String): Future[List[Persona]]
}
object PersonaOperation extends  PersonaOperation{
  private def SelectType: Brands[Persona, PersonaTableRep] =Brands[Persona,PersonaTableRep]()
  override def filterByName(name: String): Future[List[Persona]] = {
    val promiseFilterByName = Promise[List[Persona]]
    exec(promiseFilterByName,x => x.nome===name)
    promiseFilterByName.future
  }
  override def filterBySurname(surname: String): Future[List[Persona]] = {
     val promiseFilterBySurname = Promise[List[Persona]]
     exec(promiseFilterBySurname,x => x.cognome===surname)
     promiseFilterBySurname.future
  }
  private def exec(promiseFilterBySurname: Promise[List[Persona]],f:PersonaTableRep=>Rep[Boolean]): Future[Unit] = Future {
    SelectType.selectFilter(f) onComplete {
      result=>promiseFilterBySurname.success(result.get)
    }
  }
}
