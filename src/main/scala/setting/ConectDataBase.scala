package setting
import Zona._
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import scala.collection.mutable.ArrayBuffer
object ConectDataBase {

  val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig("sqlserver")
  val db   = dbConfig.db

}
