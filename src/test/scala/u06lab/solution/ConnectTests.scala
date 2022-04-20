package u06lab.solution

import org.junit.Test
import u06lab.solution.ConnectThree.Player.*
import u06lab.solution.ConnectThree.{Disk, find}
import u06lab.solution.ConnectThree.*
import org.junit.Assert.*

class ConnectTests {

  @Test
  def findTest(): Unit =
    assertEquals(Some(X), find(List(Disk(0, 0, X)), 0, 0)) // Some(X)
    assertEquals(Some(ConnectThree.Player.O), find(List(Disk(0, 0, X), Disk(0, 1, O), Disk(0, 2, X)), 0, 1)) // Some(O)
    assertEquals(None, find(List(Disk(0, 0, X), Disk(0, 1, O), Disk(0, 2, X)), 1, 1)) // None

  @Test
  def firstAvailableRowTest(): Unit =
    assertEquals(Some(0), firstAvailableRow(List(), 0)) // Some(0)
    assertEquals(Some(1), firstAvailableRow(List(Disk(0, 0, X)), 0)) // Some(1)
    assertEquals(Some(2), firstAvailableRow(List(Disk(0, 0, X), Disk(0, 1, X)), 0)) // Some(2)
    assertEquals(Some(3), firstAvailableRow(List(Disk(0, 0, X), Disk(0, 1, X), Disk(0, 2, X)), 0)) // Some(3)
    assertEquals(None, firstAvailableRow(List(Disk(0, 0, X), Disk(0, 1, X), Disk(0, 2, X), Disk(0, 3, X)), 0)) // None

  @Test
  def placeAnyDiskTest(): Unit =
    assertEquals(List(List(), List(Disk(3,0,X), Disk(2,0,X), Disk(1,0,X), Disk(0,0,X))),
      placeAnyDisk(List(), X))
    assertEquals(List(List(Disk(0,0, ConnectThree.Player.O)), List(Disk(3,0,X), Disk(2,0,X), Disk(1,0,X), Disk(0,1,X))),
      placeAnyDisk(List(Disk(0, 0, O)), X))

  @Test
  def computeAnyGameTest(): Unit =
    assertEquals(LazyList(List(List(Disk(0,3,O), Disk(0,1,O), Disk(0,2,X), Disk(0,0,X)), List(Disk(1,1,O), Disk(1,2,X), Disk(1,0,X)), List(Disk(2,1,O), Disk(2,0,X)), List(Disk(3,0,X)), List())), computeAnyGame(ConnectThree.Player.O, 4))

}
