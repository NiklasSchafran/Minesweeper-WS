package de.htwg.se.minesweeper.model.GameComponent

import scala.io.StdIn.readLine
import scala.util.Random
import de.htwg.se.minesweeper.model.FieldComponent._
import de.htwg.se.minesweeper.difficulty.DifficultyStrategy

case class Game() extends GameInterface {

  var anzahBomben = 0
  var side = 0
  var gameState: Status = Status.Playing

  private var difficultyStrategy: DifficultyStrategy = _

  def setDifficultyStrategy(strategy: DifficultyStrategy): Unit = {
    this.difficultyStrategy = strategy
    difficultyStrategy.setDifficulty(this)
  }
    
  def setGameState(status: Status): Unit = {
    gameState = status
  }

  def premierMove(x: Int, y: Int, field: FieldInterface, game: Game): Field = {
    val builder = new FieldBuilder()
      .withSize(game.side)
      .withInitialPosition(x, y)
      .withBombCount(anzahBomben)
      .addField(game)

    val newField = builder.build()
    newField
  }

  def inArea(x: Int, y: Int, side: Int): Boolean = {
    x >= 0 && x <= side && y >= 0 && y <= side
  }

  // initialises hiddenMatrix which is initialised with bombs with adjacent numbers
  def Num(x: Int, y: Int, bMatrix: Matrix[Symbols], pMatrix: Matrix[Symbols]): Matrix[Symbols] = {
    var tmpMatrix = pMatrix
    val si = bMatrix.size - 1

    if (!(inArea(x, y, si)) || pMatrix.cell(y, x) != Covered) {
      return pMatrix
    }

    var minesFound = 0
    if (isBomb(x + 1, y + 1, bMatrix)) minesFound += 1
    if (isBomb(x, y + 1, bMatrix)) minesFound += 1
    if (isBomb(x - 1, y + 1, bMatrix)) minesFound += 1
    if (isBomb(x + 1, y, bMatrix)) minesFound += 1
    if (isBomb(x - 1, y, bMatrix)) minesFound += 1
    if (isBomb(x + 1, y - 1, bMatrix)) minesFound += 1
    if (isBomb(x, y - 1, bMatrix)) minesFound += 1
    if (isBomb(x - 1, y - 1, bMatrix)) minesFound += 1

    if (minesFound == 0) {
      tmpMatrix = tmpMatrix.replaceCell(y, x, Empty)
      
      if (inArea(x + 1, y + 1, si)) tmpMatrix = Num(x + 1, y + 1, bMatrix, tmpMatrix)
      if (inArea(x, y + 1, si)) tmpMatrix = Num(x, y + 1, bMatrix, tmpMatrix)
      if (inArea(x - 1, y + 1, si)) tmpMatrix = Num(x - 1, y + 1, bMatrix, tmpMatrix)
      if (inArea(x + 1, y, si)) tmpMatrix = Num(x + 1, y, bMatrix, tmpMatrix)
      if (inArea(x - 1, y, si)) tmpMatrix = Num(x - 1, y, bMatrix, tmpMatrix)
      if (inArea(x + 1, y - 1, si)) tmpMatrix = Num(x + 1, y - 1, bMatrix, tmpMatrix)
      if (inArea(x, y - 1, si)) tmpMatrix = Num(x, y - 1, bMatrix, tmpMatrix)
      if (inArea(x - 1, y - 1, si)) tmpMatrix = Num(x - 1, y - 1, bMatrix, tmpMatrix)
      
      return tmpMatrix   
    } else {
      val symb = minesFound match {
        case 0 => Zero
        case 1 => One
        case 2 => Two
        case 3 => Three
        case 4 => Four
        case 5 => Five
        case 6 => Six
        case 7 => Seven
        case 8 => Eight
      }
      tmpMatrix = tmpMatrix.replaceCell(y, x, symb)
    }

    tmpMatrix
  }

  def setB(emptyMatrix: Matrix[Symbols], anzahlBomben: Int, x: Int, y: Int): Matrix[Symbols] = {
    val verboten = (y, x)
    var BombsMatrix = emptyMatrix
    val sizeM = emptyMatrix.size
    var AnzahlPlaziert: Int = 0
    val random = new Random()

    while (AnzahlPlaziert < anzahlBomben) {
      val xPos: Int = random.nextInt(sizeM)
      val yPos: Int = random.nextInt(sizeM)
      val tupel = (yPos, xPos)

      if (BombsMatrix.cell(yPos, xPos) != Bomb && tupel != verboten) {
        BombsMatrix = BombsMatrix.replaceCell(yPos, xPos, Bomb)
        AnzahlPlaziert += 1
      }
    }
    BombsMatrix
  }

  def isBomb(x: Int, y: Int, m: Matrix[Symbols]): Boolean = {
    val si = m.size - 1
    if (inArea(x, y, si) && m.cell(y, x) == Bomb) true
    else false
  }

  // def checkGameState(realgame: Game) =
  //   if (this.gameState == Status.Won) println("you just won!!!")
  //   else if (this.gameState == Status.Lost) println("you just Lost!!!")
  //   else print("")
}
