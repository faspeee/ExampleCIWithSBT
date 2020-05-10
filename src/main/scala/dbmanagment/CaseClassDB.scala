package dbmanagment

import java.sql.Date

trait CaseClassDB[T]
object CaseClassDB{
  case class Giorno(Quantita:Int,NomeGiorno:String,IdGiorno:Option[Int]=None)
  case class Zona(Zones:String,IdZone:Option[Int]=None)
  case class Turno(nomeTurno: String, fasciaOraria: String,id: Option[Int] = None)
  case class Persona(Nome:String,Cognome:String,DataNascita:Date,NumTelefono:String,Ruolo:Int,Matricola:Option[Int]=None)
}