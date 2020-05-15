package dbmanagment.operation
import dbmanagment.operation.ImplicitCrudG.CrudPersona
import dbmanagment.setting.implicitsGeneric.Brands
import dbmanagment.table.PersonaTable.PersonaTableRep
import dbmanagment.table.TerminaleTable.TerminaleTableRep
import dbmanagment.table.ZonaTable.ZonaTableRep
import slick.jdbc.SQLServerProfile.api._
import utils.caseclass.CaseClassDB.{Persona, Terminale, Zona}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
trait PersonaOperation extends OperationCrud[Persona]{
  def filterByName(name:String):Future[Seq[Persona]]
  def filterBySurname(surname: String): Future[List[Persona]]
  def monadicInnerJoin():Unit
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
      c <- Brands[Persona,PersonaTableRep].table
      s <- Brands[Terminale,TerminaleTableRep].table
      s2 <- Brands[Zona,ZonaTableRep].table
      if c.id === s.id &&  s.id === s2.id
    } yield (c.cognome, s.nomeTerminale,s2.Zones)
     SelectType.execJoin(monadicInnerJoin)  onComplete(t=>{
        println(t)
      })

  }
}
