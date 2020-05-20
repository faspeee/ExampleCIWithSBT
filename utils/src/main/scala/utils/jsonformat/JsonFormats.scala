package utils.jsonformat
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._
import utils.caseclass.CaseClassDB.{Settimana, StoricoContratto, Straordinario, Terminale, Turno, Zona}
import ImplicitDate._

object JsonFormats extends SprayJsonSupport with DefaultJsonProtocol {

    implicit val zonaJsonFormat: RootJsonFormat[Zona] = jsonFormat2(Zona)
    implicit val turnoJsonFormat: RootJsonFormat[Turno] = jsonFormat3(Turno)
    implicit val terminaleJsonFormat: RootJsonFormat[Terminale] = jsonFormat3(Terminale)
    implicit val straordinarioJsonFormat: RootJsonFormat[Straordinario] = jsonFormat4(Straordinario)
    implicit val storicoContrattoJsonFormat: RootJsonFormat[StoricoContratto] = jsonFormat7(StoricoContratto)
    implicit val settimanaJsonFormat: RootJsonFormat[Settimana] = jsonFormat2(Settimana)

}