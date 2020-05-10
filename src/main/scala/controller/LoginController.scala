package controller

import mock.MockLogin
import view.scenes.LoginObservervable


/**
 * Controller della schermata di login. Estende [[Controller]] con tipo [[LoginObservervable]]
 * per dire che è un controller di una view che è LoginObservable
 */
trait LoginController extends Controller[LoginObservervable]{
  /**
   * Gestione del login che mi arriva dalla view. Se ok va avanti e mostra un nuovo [[javafx.stage.Stage]]
   * altrimenti mostra messaggio di errore
   * @param name
   *             Nome utente
   * @param pass
   *             Pass utente
   */
  def login(name:String, pass:String)
}

/**
 * Companion del trait [[LoginController]]
 *
 */
object LoginController {
  def apply(): LoginController = new LoginControllerImpl()

  private class LoginControllerImpl extends AbstractController[LoginObservervable] with LoginController{
    private val model: MockLogin = MockLogin()
    override def login(name: String, pass: String): Unit ={
      if (model.login(name,pass)){
        myView.goodLogin()
      } else{
        myView.errorLogin()
      }
    }
  }
}