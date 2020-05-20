package setting
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import scala.collection.mutable.ArrayBuffer
object ConectDataBase {

  val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig("sqlserver")
  val db   = dbConfig.db

}
