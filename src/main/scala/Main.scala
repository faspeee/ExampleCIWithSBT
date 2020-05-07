
package prova

import setting.{ConectDataBase, FilterDataBase, InsertDataBase, SelectDataBase}

case class Person(val nome: String, val cognome: String, val matricola: Int, val ruolo: Int){
 var a = 2
 var i = 1
 if (!(a == 2)) {  }  // Noncompliant
 val b = !(i < 10)  // Noncompliant
}

object Main extends App{
  val connection = ConectDataBase
   FilterDataBase.filterByZona()
   FilterDataBase.filterByZonaF()
   InsertDataBase.insertZona()
   SelectDataBase.selectGiornoAwait()
   SelectDataBase.selectZonaFuture()
   SelectDataBase.selectZonaStream()
// println("hola")
 val names = List("Marco", "Mario", "Fabio", "Franco", "Luca", "Culio")
 val surnames = List("Rossi", "Verdi", "Bianchi", "Neri", "Russi", "Ban")
 var people = List()

 val r = scala.util.Random
 def combination (names: List[String], surnames: List[String]) : List[Person] =
     for (name <- names;
          surname <- surnames;
          mat = r.nextInt(10000)  ;
          rol = 0) yield (Person(name, surname, mat, rol))

 //print(people.appendedAll(combination(names, surnames)))
 //people.foreach(t => println(t))

 def isGordo(s:String) = s.equals("Fabian")

}
