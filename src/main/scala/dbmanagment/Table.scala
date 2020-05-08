package dbmanagment
import DBConnection._
import slick.jdbc.SQLServerProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
sealed trait Operation[F]{
  def create[A](element:F):Future[Option[Int]]
  def read(element:F):Option[F]
  def update(element:F):Option[F]
  def delete(element:F):Option[F]
}
object Operation{
  implicit class RichOperation[F:Operation, A](t:F){
    def create():Future[Option[Int]]=implicitly[Operation[F]].create(t)
    def read():Option[F]=implicitly[Operation[F]].read(t)
    def update():Option[F]=implicitly[Operation[F]].update(t)
    def delete():Option[F]=implicitly[Operation[F]].delete(t)
  }
}
object implicits {
  import ZonaTable._
  implicit object Insert extends Operation[Zona] {
    override def create[A](element: Zona): Future[Option[Int]] = {
      db().run(zone += element).map(t=>Some(t))
    }

    override def read(element: Zona): Option[Zona] = ???

    override def update(element: Zona): Option[Zona] = ???

    override def delete(element: Zona): Option[Zona] = ???
  }

}
object tryCase extends App{
  import Operation._,implicits._
  import ZonaTable._
  val s = Zona("")
  s.create()
}