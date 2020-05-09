package controller

import view.scenes.LoginObservervable


trait LoginController extends Controller[LoginObservervable]{
  def login(name:String, pass:String)
}

object LoginController {
  def apply(): LoginController = new LoginControllerImpl()

  private class LoginControllerImpl extends AbstractController[LoginObservervable] with LoginController{
    override def login(name: String, pass: String): Unit =
      println(name,pass)
  }
}
