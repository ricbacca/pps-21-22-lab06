package u06lab.solution

import java.util.OptionalInt

object ConnectThree extends App:
  val bound = 3
  enum Player:
    case X, O
    def other: Player = this match
      case X => O
      case _ => X

  case class Disk(x: Int, y: Int, player: Player)
  /**
   * Board:
   * y
   *
   * 3
   * 2
   * 1
   * 0
   *   0 1 2 3 <-- x
   */
  type Board = Seq[Disk]
  type Game = Seq[Board]

  import Player.*

  /**
   * if P(x, y) is into the board, return some of...
   */
  def find(board: Board, x: Int, y: Int): Option[Player] =
    board.find(p => p.x == x && p.y == y).map(x => x.player)

  /**
   * return first available row based on the board
   */
  def firstAvailableRow(board: Board, x: Int): Option[Int] =
    if !board.exists(e => e.x == x) then
      Some(0)
    else
      val max = board.filter(e => e.x == x).map(e => e.y).max
      if max < bound then
        Some(max+1)
      else
        None

  def placeAnyDisk(board: Board, player: Player): Seq[Board] =
    var tempBoard: Board = List()
    for i <- 0 to bound do
      tempBoard.+:=(Disk(i, firstAvailableRow(board, i).get, player))
    Seq(board, tempBoard)

  def computeAnyGame(player: Player, moves: Int): LazyList[Game] =
    var game:Game = Seq()
    val opponent:Player = Player.X
    for x <- moves to 0 by -1 do
      var tempBoard:Board = Seq()
      for y <- 0 to bound-x by +2 do
        tempBoard.+:=(Disk(x, y, opponent))
      for y <- 1 to bound-x by +2 do
        tempBoard.+:=(Disk(x, y, player))
      game.+:=(tempBoard)
    LazyList(game)


  def printBoards(game: Seq[Board]): Unit =
    for
      y <- bound to 0 by -1
      board <- game.reverse
      x <- 0 to bound
    do
      print(find(board, x, y).map(_.toString).getOrElse("."))
      if x == bound then
        print(" ")
        if board == game.head then println()
