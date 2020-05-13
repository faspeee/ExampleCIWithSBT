package controller

import javafx.application.Application
import javafx.stage.Stage
import model.Model
import view.{FraMainView, FraView, View}

class FraController {
}

object FraController{
  private var viewManager: FraMainView = _
  private var model: Model = _

  def apply(model: Model): FraController = {
    this.model = model
    this.viewManager = new FraMainView
    this.viewManager.d
    new FraController
  }

  def setObserver(view: FraView) = model.resetObserver(view)

  def aggiungi(n:Int) = model.increase(n)

  def ritorna() = model.get()
}



