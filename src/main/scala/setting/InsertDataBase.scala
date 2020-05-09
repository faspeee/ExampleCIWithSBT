package setting
import dbmanagment.GiornoTable.{Giorno, GiornoTableRep}
import dbmanagment.{GenericCRUD, ZonaTable}
import dbmanagment.ZonaTable.{Zona, ZonaTableRep}
import dbmanagment.implicitsGeneric.BrandsZona
import scala.concurrent.ExecutionContext.Implicits.global
import dbmanagment.implicitsGeneric2.BrandsGiorno
object InsertDataBase {
   def insertZona()= {
    try {
      implicit val implicitStringOrdering: GenericCRUD[ZonaTableRep,Zona] = BrandsZona

      import ZonaTable._
      val s = Zona("Giannis")
      val f = BrandsZona.create(s)
      f onComplete { posts =>
        for (post <- posts) println(post)
      }
       insertGiorno()
     /* Await.result(db.run(DBIO.seq(
        zone += ZonaSet("Gianni"),
        zone += ZonaSet("Fabian"),
        zone += ZonaSet("Francesco")
      )).flatMap { _ =>
        //#readall
        // Read all coffees and print them to the console
        println("Zona:")

        db.run(zone.result).map(_.foreach(t =>
          println("  " + t.IdZone + "\t" + t.Zone)
        ))
      }, Duration.Inf)
*/
    // Equivalent SQL code:
    // select COF_NAME, SUP_ID, PRICE, SALES, TOTAL from COFFEES
    //#readall

   } finally  println("insertZona:")
  }
  def insertGiorno(): Unit ={
    implicit val implicitStringOrdering2: GenericCRUD[GiornoTableRep,Giorno] = BrandsGiorno
    val ss = Giorno(11,"Lunedi")
    val ff = BrandsGiorno.create(ss)
    ff onComplete { posts =>
      for (post <- posts) println(post)
    }
  }
}
