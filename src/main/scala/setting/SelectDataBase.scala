package setting
import ConectDataBase._
import setting.Giorno._
import setting.Zona._
import slick.basic.DatabasePublisher
import slick.jdbc.SQLServerProfile.api._

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
object SelectDataBase {
  def selectZonaStream()= {
    try {
      println("Zona Stream:")
      val q2 = for (c <- zone) yield c.zona
      val a2 = q2.result
      val p: DatabasePublisher[String] = db.stream(a2)
      // .foreach is a convenience method on DatabasePublisher.
      // Use Akka Streams for more elaborate stream processing.
      p.foreach { s => println(s"Element: $s") }

    } finally  println("selectZonaStream:")
  }
  def selectZonaFuture()= {
    try {

      println("Zona Future:")
      val q = for (c <- zone) yield c.zona
      val a = q.result
      val f: Future[Seq[String]] = db.run(a)
      f.onComplete {
        case Success(s) => println(s"Result: $s")
        case Failure(t) => t.printStackTrace()
      }

    } finally  println("selectZonaFuture:")
  }
  def selectGiornoAwait()= {
    try {

      println("Giorno:")
      Await.result(db.run(giorno.result).map(_.foreach(t=>
        println("  " + t.IdGiorno + "\t" + t.Quantita + "\t" + t.NomeGiorno)
      )), Duration.Inf)

    } finally  println("selectGiornoAwait:")
  }
}
