import org.scalatest.funsuite.AnyFunSuite
import Main._

/**
 * Testing Class
 */
class Test extends AnyFunSuite {
  test("Prova"){
    assert(isGordo("Fabian") == true)
  }
}
