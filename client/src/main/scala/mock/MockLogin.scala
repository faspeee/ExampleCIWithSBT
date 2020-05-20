package mock

trait MockLogin {
  def login(name:String,pass:String):Boolean
}

object MockLogin{
  def apply(): MockLogin = new mockLoginImpl()

  private class mockLoginImpl() extends MockLogin{
    override def login(name: String, pass: String): Boolean = (name,pass) match {
      case ("user","pass") => true
      case _ => false
    }
  }
}
