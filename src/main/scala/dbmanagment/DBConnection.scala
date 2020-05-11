package dbmanagment
import slick.basic.DatabaseConfig
import slick.dbio.{DBIOAction, NoStream}
import slick.jdbc.JdbcProfile
trait DBS{
  private val dbCo:DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig("sqlserver")
  val database = dbCo.db
}
trait DB[C <: GenericTable[T], T] extends DBS {
  def run[R](a: DBIOAction[R, NoStream, Nothing]) = database.run(a)
}