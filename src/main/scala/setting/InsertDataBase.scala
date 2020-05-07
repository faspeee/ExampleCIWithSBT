package setting
import ConectDataBase._
import setting.Zona.{ZonaSet, zone}
import slick.dbio.DBIO
import slick.jdbc.SQLServerProfile.api._

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
object InsertDataBase {
  def insertZona()= {
    try {

      Await.result(db.run(DBIO.seq(
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

    // Equivalent SQL code:
    // select COF_NAME, SUP_ID, PRICE, SALES, TOTAL from COFFEES
    //#readall

   } finally  println("insertZona:")
  }
}
