package setting
import dbmanagment.CaseClassDB.{Giorno, Zona}
import dbmanagment.GenericCrudClass._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
object InsertDataBase {
  var id = 0
   def insertZona()= {
    try {
      val s = Zona("Giannis")
      insert(s) onComplete { posts =>
        for (post <- posts) {id = post;println(post)}
      }
   }
  }
  def selectZona(): Unit ={
    val s = Zona("",Some(id))
     select(s)  onComplete { posts =>
       for (post <- posts) println(post)
     }
  }
  def updateZona(): Unit ={
    val s = Zona("el mejor es Fabian :)",Some(id))
    update(s).onComplete {
      case Success(x) => println(x)
      case Failure(x) => println(x)
    }
  }
  def deleteZona(): Unit ={
    val s = Zona("",Some(id))
    delete(s)  onComplete { posts =>
      for (post <- posts) println(post)
    }
  }
}
