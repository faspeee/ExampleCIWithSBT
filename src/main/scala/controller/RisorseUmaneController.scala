package controller

import mock.{Conducente, MockConducenti}
import view.scenes.RisorseUmaneView

trait RisorseUmaneController extends Controller[RisorseUmaneView]{
  def assumi(assunzione:Conducente)
  def licenzia(ids: Set[String])
  def listaConducenti():Set[Conducente]
}

object RisorseUmaneController{
  private val instance = new RisorseUmaneControllerImpl()
  def apply(): RisorseUmaneController = instance

  private class RisorseUmaneControllerImpl() extends AbstractController[RisorseUmaneView] with RisorseUmaneController{
    val conducenti:MockConducenti = MockConducenti()

    override def assumi(assunzione: Conducente): Unit = {
      conducenti.assumi(assunzione)
    }

    override def licenzia(ids: Set[String]): Unit = {
      conducenti.licenzia(ids)
      myView.refreshLicenzia()
    }

    override def listaConducenti(): Set[Conducente] =
      conducenti.conducenti()
  }
}
