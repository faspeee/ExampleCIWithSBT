package model

import utils.observerPattern.Observable
import view.FraView

class Model (private var n: Int = 0) extends Observable[Model]{
  def resetObserver(view: FraView) = super.setObserver(view)

  def update(newN : Int) = n = newN
  def get(): Int = n
  def increase(add: Int) = {
    n += add
    notifyObserver()
  }
}
