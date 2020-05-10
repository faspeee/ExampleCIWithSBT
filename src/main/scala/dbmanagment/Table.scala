package dbmanagment

import scala.concurrent.Future
import CaseClassDB._
sealed trait Operation[F]{
  def creates[A](element:F)
  def read(element:F):Option[F]
  def update(element:F):Option[F]
  def delete(element:F):Option[F]
}
object Operation{
  implicit class RichOperation[F:Operation](t:F){
    def creates()  =implicitly[Operation[F]].creates(t)
    def read():Option[F]=implicitly[Operation[F]].read(t)
    def update():Option[F]=implicitly[Operation[F]].update(t)
    def delete():Option[F]=implicitly[Operation[F]].delete(t)
  }
}
object implicits {
  import ZonaTable._
  implicit object Insert extends Operation[Zona] {
    override def creates[A](element: Zona)  = ???
    override def read(element: Zona): Option[Zona] = read(element)
    override def update(element: Zona): Option[Zona] = ???
    override def delete(element: Zona): Option[Zona] = delete(element)
  }

}
object tryCase extends App{
  import Operation._,implicits._
  import ZonaTable._
  case class perro()
  val ee = perro

  val s = Zona("")
  s.read()
}