package dbmanagment

import java.sql.Date

object CaseClassDB{
  final case class Giorno(Quantita:Int,NomeGiorno:String,IdGiorno:Option[Int]=None)
  final case class Zona(Zones:String,IdZone:Option[Int]=None)
  final case class Turno(nomeTurno: String, fasciaOraria: String,id: Option[Int] = None)
  final case class Persona(Nome:String,Cognome:String,DataNascita:Date,NumTelefono:String,Ruolo:Int,Matricola:Option[Int]=None)
  final case class Terminale(NomeTerminale:String,IdTerminale:Option[Int]=None)

  def headOption[T](xs: List[T]): Option[T] = xs match {
    case x :: _ => Some(x)
    case _ => None
  }
}

