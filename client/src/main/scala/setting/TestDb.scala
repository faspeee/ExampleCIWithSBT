package setting

import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.jdbc.SQLServerProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps
import scala.util.Success

object TestDb {

  case class Turno(
                   id: Option[Int] = None,
                   nomeTurno: String,
                   fasciaOraria: String)

  class TurnoTable(tag: Tag) extends Table[Turno](tag, "TurnoSet") {
    def id = column[Int]("idTurno",O.PrimaryKey,O.AutoInc)

    def nomeTurno = column[String]("NomeTurno")

    def fasciaOraria = column[String]("FasciaOraria")

    def * = (id.?, nomeTurno, fasciaOraria) <> (Turno.tupled,Turno.unapply)
  }

  case class Giorno(quantita: Int,
                    nomeGiorno: String,
                    id: Option[Int]=None)

  class GiornoTable(tag: Tag) extends Table[Giorno](tag, "GiornoSet") {
    def id = column[Int]("idGiorno",O.PrimaryKey,O.AutoInc)

    def quantita = column[Int]("Quantita")

    def nomeGiorno = column[String]("NomeGiorno")

    def * = (quantita, nomeGiorno,id.?) <> (Giorno.tupled, Giorno.unapply)
  }

  val turni = TableQuery[TurnoTable]
  val giorni = TableQuery[GiornoTable]

  def mainp(args: Array[String]): Unit = {
    val dbCo:DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig("sqlserver")
    val db:JdbcProfile #Backend#Database = dbCo.db

    val q1 = giorni.filter(_.quantita > 10)
    val q2 = giorni.sortBy(_.nomeGiorno.desc.nullsFirst)

    val critLun = Option("Lunedi")

    val q4 = giorni.filter{
      giorno =>
        List(critLun.map(giorno.nomeGiorno === _)).collect({case Some(c) => c}).reduceLeftOption(_ || _).getOrElse(true: Rep[Boolean])
    }

    val q3 = giorni.filterOpt(critLun)(_.nomeGiorno === _)

    val a = giorni.map(_.quantita)
    val a1 = a.sum
    val a2 = a.avg
    val action = q3.delete

    var mmm = false
    val res = db.run(action)
    res.onComplete({
      case Success(x) => println("ELIMINATE: ",x); mmm = true
      case _ => println("DIOCANE")
    })
//    while(!mmm){
//    }
    println(action.statements.head)

    val insert = DBIO.seq(
      giorni += Giorno(15,"Lunedì"),
      giorni += Giorno(20,"Martedi"),
      giorni ++=Seq(
        Giorno(30,"Mercoledì"),
        Giorno(19,"Giovedi"),
      ),
      giorni.map(g => (g.quantita,g.nomeGiorno)) += (13,"Venerdi")
    )
    val proviam = (giorni returning giorni.map(_.id)) += Giorno(10,"Sabato")
    val sql = giorni.insertStatement
    println(sql)
    db.run(proviam).onComplete{
      case Success(x) => println("inserite ", x, proviam)
      case _ => println("DIO")
    }
 }
}