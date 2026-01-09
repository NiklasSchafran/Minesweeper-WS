package de.htwg.se.minesweeper.model.FieldComponent

import de.htwg.se.minesweeper.model.GameComponent._

case class Field(matrix: Matrix[Symbols], bomben: Matrix[Symbols]) extends FieldInterface {
  val size: Int = matrix.size
  var playerMatrix: Matrix[Symbols] = matrix
  var bombenMatrix: Matrix[Symbols] = bomben

  // Erstellt ein neues Feld
  def newField(size: Int, filling: Symbols): FieldInterface = {
    // Typ explizit als Symbols angeben, damit Invarianz-Fehler wegfallen
    val filledMatrix: Matrix[Symbols] = new Matrix(
      Vector.fill(size)(Vector.fill(size)(filling): Vector[Symbols])
    )
    val emptyMatrix: Matrix[Symbols] = new Matrix(
      Vector.fill(size)(Vector.fill(size)(Empty): Vector[Symbols])
    )
    new Field(filledMatrix, emptyMatrix)
  }

  def bar(cellWidth: Int = 3, cellNum: Int = 3): String =
    (("+" + "-" * cellWidth) * cellNum) + "+" + sys.props("line.separator")

  def cells(row: Int = 3, cellWidth: Int = 3): String =
    matrix.row(row).map(_.toString)
      .map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2))
      .mkString("|", "|", "|") + sys.props("line.separator")

  def mesh(cellWidth: Int = 3): String =
    (0 until size).map(cells(_, cellWidth)).mkString(bar(cellWidth, size), bar(cellWidth, size), bar(cellWidth, size))

  def open(x: Int, y: Int, spiel: GameInterface): (FieldInterface, Status) = {
    if (bombenMatrix.cell(y, x) == Bomb) {
      spiel.setGameState(Status.Lost)
      playerMatrix = playerMatrix.replaceCell(y, x, Bomb)
      val nextField = new Field(playerMatrix, bombenMatrix)
      (nextField, Status.Lost)
    } else {
      playerMatrix = spiel.Num(x, y, bombenMatrix, playerMatrix)
      val nextField = new Field(playerMatrix, bombenMatrix)
      (nextField, spiel.gameState)
    }
  }

  def cell(row: Int, col: Int): Symbols = matrix.cell(row, col)

  override def toString(): String = mesh()
}
