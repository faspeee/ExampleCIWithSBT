package controller

/**
 * Trait for a generic Controller. Adesso ha solo la possibilità farsi settare una view da guardare.
 * Generico in A per poter settare le diverse view
 * @tparam A
 *           Tipo della view da guardare
 */
trait Controller[A] {
  def setView(view:A)
}

/**
 * Template class(?) che serve a dire come i controller si settano le view, è in comune quindi ok.
 * @tparam A
 *           Tipo della view da guardare
 */
abstract class AbstractController[A] extends Controller[A]{
  protected var myView:A = _
  override def setView(view: A): Unit =
    myView = view
}
