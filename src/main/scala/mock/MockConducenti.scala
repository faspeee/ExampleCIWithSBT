package mock

case class Conducente(nome:String, cognome:String, contratto:String, turno:String, giorno1:String, giorno2:String, terminale:String, telefono:String)

trait MockConducenti {
  def assumi(assumi:Conducente)
  def conducenti():Set[Conducente]
  def licenzia(ids:Set[String])
}

object MockConducenti{
  def apply(): MockConducenti = new MockConducentiImpl()

  private class MockConducentiImpl extends MockConducenti{
    private var conducent:Set[Conducente] = Set()

    override def assumi(assumi: Conducente): Unit = {
      conducent = conducent + assumi
      println(conducent)
    }

    override def conducenti(): Set[Conducente] = conducent

    override def licenzia(ids: Set[String]): Unit ={
      ids.foreach(id => conducent = conducent.filter(_.telefono!=id))
    }
  }
}
