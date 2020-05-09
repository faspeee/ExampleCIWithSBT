package controller

import view.scenes.ActiveScene

trait Controller[A] {
  def setView(view:A)
}

abstract class AbstractController[A] extends Controller[A]{
  protected var myView:A = _
  override def setView(view: A): Unit =
    myView = view
}
