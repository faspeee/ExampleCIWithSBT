package controller

import dbmanagment.CaseClassDB.Zona
import dbmanagment.ImplicitCrud._
import dbmanagment.ImplicitCrudG._
import scalafx.application.Platform

import scala.concurrent.duration.Duration
import view.scenes.ZonaView

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success

trait ZonaController extends Controller[ZonaView]{
  def insertZone(nome:String)
  def removeZones(ids:Set[Int])
  def loadZones()
}

object ZonaController {
  private val instance = new ZonaControllerImpl()

  def apply(): ZonaController = instance

  private class ZonaControllerImpl() extends AbstractController[ZonaView] with ZonaController{

    override def insertZone(nome: String): Unit = {
      insert(Zona(nome)).andThen(_=> loadZones())
    }

    override def removeZones(ids: Set[Int]): Unit ={
      var list: List[Zona] = List()
      ids.foreach(x => list = Zona("",Some(x))::list)
      deleteAll(list).andThen(_=> loadZones())
    }

    override def loadZones(): Unit = {
      selectAll[Zona].onComplete{
        case Success(x) => myView.setZones(x)
        case _ => println("error")
      }
    }
  }

}