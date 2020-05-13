package mock
import scala.language.postfixOps

trait MockTurno {
  def insertTurno(nomeTurno:String, fasciaOraria:String):List[(String,String)]
}

object MockTurno{
  def apply(): MockTurno = new MockTurnoImpl()

  private class MockTurnoImpl() extends MockTurno{
    var turni:List[(String,String)] = List()

    override def insertTurno(nomeTurno: String, fasciaOraria: String): List[(String,String)] = {
      turni = (nomeTurno,fasciaOraria) :: turni
      turni
    }
  }
}