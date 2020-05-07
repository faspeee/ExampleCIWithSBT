package dbmanagment

import scala.util.Success
import scala.concurrent.ExecutionContext.Implicits.global

object Test extends App{
  val turnoTable:TurnoTable = TurnoTable()
  val id = turnoTable.insertTurno("Mattina","4-8")

  id.onComplete{
    case Success(x) => println(x)
    case _ => println("Errore")
  }

  while(true){

  }
}
