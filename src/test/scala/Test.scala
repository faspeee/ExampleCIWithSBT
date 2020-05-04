import org.scalatest._
import Main._

class Test extends FunSuite{
  test("Prova"){
    assert(isGordo("Fabian") == true)
  }
}
