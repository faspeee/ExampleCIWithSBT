package utils.jsonformat
 
import routes.TurnoRoute.Turno
import spray.json.DefaultJsonProtocol
import utils.caseclass.CaseClassDB.Zona
object JsonFormats {
    import DefaultJsonProtocol._

    final case class ZonaAll(zone:List[Zona])
    implicit val zonaJsonFormat = jsonFormat2(Zona)
    implicit val zonaAllJsonFormat = jsonFormat1(ZonaAll)
    implicit val turnoJsonFormat = jsonFormat2(Turno)


}
