package dbmanagment.operation
import dbmanagment.operation.ImplicitCrudG.CrudPersona
import dbmanagment.setting.implicitsGeneric.Brands
import dbmanagment.table.PersonaTable.PersonaTableRep
import dbmanagment.table.TerminaleTable.TerminaleTableRep
import slick.jdbc.SQLServerProfile.api._
import utils.caseclass.CaseClassDB.{Login, Persona}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
trait PersonaOperation extends OperationCrud[Persona]{
  def filterByName(name:String):Future[Seq[Persona]]
  def filterBySurname(surname: String): Future[List[Persona]]
  def monadicInnerJoin():Unit
  def login(login:Login):Future[List[Persona]]
}
object PersonaOperation extends  PersonaOperation{
  private def SelectType: Brands[Persona, PersonaTableRep] =Brands[Persona,PersonaTableRep]()
  override def filterByName(name: String): Future[List[Persona]] = {
    val promiseFilterByName = Promise[List[Persona]]
    execFilter(promiseFilterByName,x => x.nome===name)
    promiseFilterByName.future
  }
  override def filterBySurname(surname: String): Future[List[Persona]] = {
     val promiseFilterBySurname = Promise[List[Persona]]
    execFilter(promiseFilterBySurname,x => x.cognome===surname)
     promiseFilterBySurname.future
  }
  private def execFilter(promiseFilterBySurname: Promise[List[Persona]],f:PersonaTableRep=>Rep[Boolean]): Future[Unit] = Future {
    SelectType.selectFilter(f) onComplete {
      result=>promiseFilterBySurname.success(result.get)
    }
  }
   override def monadicInnerJoin(): Unit = {
    val monadicInnerJoin = for {
     c <- TableQuery[PersonaTableRep]
     s <- TableQuery[TerminaleTableRep]
     if c.id===s.id
    } yield (c.nome,s.nomeTerminale)
     SelectType.execJoin(monadicInnerJoin)  onComplete(t=>{
        println(t)
      })

  }
  override def login(login: Login): Future[List[Persona]] = {
    val promiseFilterBySurname = Promise[List[Persona]]
    execFilter(promiseFilterBySurname,x => x.nome===login.user && x.cognome===login.password)
    promiseFilterBySurname.future

  }
}
