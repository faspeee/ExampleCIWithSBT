package dbmanagment

import scala.concurrent.Future
import CaseClassDB._
import dbmanagment.implicitsGeneric.Brands
sealed trait Operation[F]{
  def create[A](element:F): Future[Int]
  def read[F](element:F):Future[Option[F]]
  def update(element:F): Future[Int]
  def delete(element:F): Future[Int]
}
object Operation{
  implicit class RichOperation[F:Operation](t:F){
    def create(): Future[Int] =implicitly[Operation[F]].create(t)
    def read():Future[Option[F]] =implicitly[Operation[F]].read(t)
    def update(): Future[Int] =implicitly[Operation[F]].update(t)
    def delete(): Future[Int] =implicitly[Operation[F]].delete(t)
  }
}
object implicits {
  import ZonaTable._
  implicit object Insert extends  Operation[Zona]{
    override def create[A](element: Zona): Future[Int] = ???
    override def read[F](element: F):Future[Option[F]] = ??? //select(element.asInstanceOf[Zona].IdZone.get)
    override def update(element: Zona): Future[Int] = Brands().update(element)
    override def delete(element: Zona) : Future[Int] = Brands().delete(element.IdZone.get)
  }

}
object tryCase extends App{

  import scala.concurrent.ExecutionContext.Implicits.global
  import Operation._,implicits._
  import ZonaTable._
  case class perro()
  val ee = perro
  val s = Zona("")
  s.create()  onComplete { posts =>
    for (post <- posts) println(post)
  }
  while(true){}
}