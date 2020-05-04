
package test.scala
import org.scalatest._
import prova.Main._

class Test extends FunSuite{
  test("Prova"){
    assert(isGordo("Fabian") == true)
  }
}

