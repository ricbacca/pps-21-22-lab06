package u06lab.solution

import org.junit.Assert.*
import org.junit.Test

class CombinerTests {

  val f: Functions = FunctionsImpl

  @Test
  def testSum(): Unit =
    assertEquals(60.1, f.sum(List(10.0, 20.0, 30.1)), 0.001)
    assertEquals(0.0, f.sum(List()), 0.001)

  @Test
  def testConcat(): Unit =
    assertEquals("abc", f.concat(Seq("a", "b", "c")))
    assertEquals("", f.concat(Seq()))

  @Test
  def testMax(): Unit =
    assertEquals(3, f.max(List(-10, 3, -5, 0)))
    assertEquals(Integer.MIN_VALUE, f.max(List()))


}
