package dbmanagment
import slick.basic.DatabaseConfig
import slick.dbio.{DBIOAction, NoStream}
import slick.jdbc.JdbcProfile
trait DB[C <: GenericTable[T], T]{
  private val dbCo:DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig("sqlserver")
  private val database = dbCo.db
  def run[R](a: DBIOAction[R, NoStream, Nothing]) = database.run(a)
}