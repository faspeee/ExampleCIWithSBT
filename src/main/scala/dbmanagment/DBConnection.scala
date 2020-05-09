package dbmanagment
import slick.jdbc.SQLServerProfile.api._
import slick.basic.DatabaseConfig
import slick.dbio.{DBIOAction, NoStream}
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

trait DB[C <: GenericTable[T], T]{
  protected val table: TableQuery[C]
  private val dbCo:DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig("sqlserver")
  val database = dbCo.db
  def run[R](a: DBIOAction[R, NoStream, Nothing]) = database.run(a)
}


