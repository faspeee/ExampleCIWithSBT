package controller

import java.sql.Date

import dbmanagment.CaseClassDB.{Persona, Terminale}
import view.scenes.RisorseUmaneView
import dbmanagment.ImplicitCrudG._
import dbmanagment.ImplicitCrud._

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
      insert(Persona(assunzione.Nome,assunzione.Cognome,new java.sql.Date(millis),assunzione.NumTelefono,10,assunzione.IdTerminale))
    }

    override def licenzia(ids: Set[Int]): Unit = {
      var list: List[Persona] = List()
      ids.foreach(x => list = Persona("","",new Date(1),"",1,None,Some(x))::list)
      deleteAll(list).andThen(_=> loadLicenzia())


    }

    override def loadAssumi(): Unit = {
      selectAll[Terminale].onComplete{
        case Success(x) => myView.assumiToLoad(x)
        case _ => println("")
      }
    }

    override def loadLicenzia(): Unit =
      selectAll[Persona].onComplete{
        case Success(x) => myView.licenziaToLoad(x)
        case _ => println("rrrr")
      }
  }
}
