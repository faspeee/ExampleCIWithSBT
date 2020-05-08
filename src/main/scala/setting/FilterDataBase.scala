package setting
import ConectDataBase._
//import dbmanagment.Zona.{Zona, ZonaSet, zone}
import slick.dbio.DBIO
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
object FilterDataBase {
   /* def filterByZona(): Unit ={
      val q1 = zone.filter(_.zona==="1")
      Await.result(db.run(q1.result).map(_.foreach(t=>println("song with id "+t))),Duration.Inf)

    }

   def filterByZonaF(): Unit ={
     val q1 = zone.filter(_.zona==="1")

     val f: Future[Seq[ZonaSet]]= {
       db.run(q1.result)
     }

     var t = true
     f onComplete { posts =>
       for (post <- posts) println(post)
       t=false
     }


  }*/
}
