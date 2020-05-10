package setting
import dbmanagment.GenericCrudClass._
import scala.concurrent.ExecutionContext.Implicits.global
object InsertDataBase {
   def insertZona()= {
    try {
     // implicit val implicitStringOrdering: GenericCRUD[Zona,ZonaTableRep] = Brands[ Zona,ZonaTableRep]()
     //  val s = Zona("Giannis")
      //  val f = implicitStringOrdering.create(s)
      //   f onComplete { posts =>
      //     for (post <- posts) println(post)
      //   }
      case class Zonas(s:String,ss:String,sss:Option[Int]=None)
      val s = Zonas("Giannis","2")
      create(s)
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
  //  implicit val implicitStringOrdering2: GenericCRUD[Giorno,GiornoTableRep] = Brands[ Giorno,GiornoTableRep]()
   // val ss = Giorno(11,"Lunedi")
   // val ff = implicitStringOrdering2.create(ss)
   // ff onComplete { posts =>
   //   for (post <- posts) println(post)
   // }
  }
}
