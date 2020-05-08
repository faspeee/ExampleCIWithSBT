package controller

import dbmanagment.TurnoTable
import view.MainView

import scala.Option
import scala.util.Success
import scala.concurrent.ExecutionContext.Implicits.global


trait MainController{
  def setView(view:MainView)
  def loadOnView()
  def turnoAdded(nome:String, fascia:String)
}
object MainController {
  def apply(model:TurnoTable): MainController = new MainControllerImpl(model)

  private class MainControllerImpl(val model:TurnoTable) extends MainController {
    var mainView:Option[MainView] = None //se lo facciamo così possiamo anche fare senza grafica, basta dare roba al posto di None
    override def setView(view: MainView) =
      mainView=Option(view)

    override def loadOnView(): Unit =
      mainView match {
        case Some(x) => x.loadComponent()
      }

    override def turnoAdded(nome: String, fascia: String): Unit ={
      println("arrivati -> " ,nome,fascia)
     val inserted = model.insertTurno(nome,fascia)
      inserted.onComplete{
        case Success(x) => println(x)
        case _ => println("error")
      }
    }
  }
}
