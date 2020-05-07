package dbmanagment

import slick.basic.DatabaseConfig
import slick.jdbc.{JdbcProfile}

object DBConnection {
  private val dbCo:DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig("sqlserver")
  private val database:JdbcProfile #Backend#Database = dbCo.db

  /**
   * Gets the instance of the database active in the session.
   * @return
   *         The DB instance active for the session
   */
  def db(): JdbcProfile #Backend#Database = database
}
