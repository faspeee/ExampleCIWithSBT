package utils.observerPattern



//Observer class
trait Observer[S] {
  def notification(subject: S);
}

trait Observable[S] { // Publisher
  this: S =>
  private var observer: Observer[S] = _
  //private var observers: List[Observer[S]] = Nil

  def setObserver(observer: Observer[S]) = this.observer = observer

  def notifyObserver() = observer.notification(this)

  //def addObserver(observer: Observer[S]) = observers = observer :: observers

  //def notifyObservers() = observers.foreach(_.notification(this))

  //def removeObserver(observer: Observer[S]) = observers.filter(x -> !(x equals observer))
  //def removeObsevers() = this.observers = Nil
}
