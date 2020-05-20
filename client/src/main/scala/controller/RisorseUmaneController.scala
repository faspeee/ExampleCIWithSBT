package controller

import java.sql.Date

import utils.caseclass.CaseClassDB.{Persona, Terminale}
import view.scenes.RisorseUmaneView
import dbmanagment.operation.ImplicitCrudG._
import dbmanagment.operation.{PersonaOperation, TerminaleOperation}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success

trait RisorseUmaneController extends Controller[RisorseUmaneView]{
  def loadAssumi()
  def loadLicenzia()
  def assumi(assunzione:Persona)
  def licenzia(ids: Set[Int])
}

object RisorseUmaneController{
  private val instance = new RisorseUmaneControllerImpl()
  def apply(): RisorseUmaneController = instance

  private class RisorseUmaneControllerImpl() extends AbstractController[RisorseUmaneView] with RisorseUmaneController{

    override def assumi(assunzione: Persona): Unit = {
      val millis = System.currentTimeMillis
      PersonaOperation.insert(Persona(assunzione.nome,assunzione.cognome,new java.sql.Date(millis),assunzione.numTelefono,10,assunzione.idTerminale))
    }

    override def licenzia(ids: Set[Int]): Unit = {
      var list: List[Persona] = List()
      ids.foreach(x => list = Persona("","",new Date(1),"",1,None,Some(x))::list)
     PersonaOperation.deleteAll(list).andThen(_=> loadLicenzia())


    }

    override def loadAssumi(): Unit = {
      TerminaleOperation.selectAll.onComplete{
        case Success(x) => myView.assumiToLoad(x)
        case _ => println("")
      }
    }

    override def loadLicenzia(): Unit = {
      PersonaOperation.selectAll.onComplete {
        case Success(x) => myView.licenziaToLoad(x)
        case _ => println("rrrr")
      }
    }
  }
}
