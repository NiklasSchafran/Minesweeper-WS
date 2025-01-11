package de.htwg.se.minesweeper.model.FieldComponent

import de.htwg.se.minesweeper.model.GameComponent.*

case class Field(matrix: Matrix[Symbols], bomben: Matrix[Symbols]) extends FieldInterface {
    val size: Int = matrix.size
    var playerMatrix: Matrix[Symbols] = matrix
    var bombenMatrix: Matrix[Symbols] = bomben

    def newField(size: Int, filling: Symbols): FieldInterface = {
        val filledMatrix = new Matrix[Symbols](Vector.fill(size, size)(filling))
        val emptyMatrix = new Matrix[Symbols](Vector.fill(size, size)(Symbols.Empty))
        new Field(filledMatrix, emptyMatrix)
    }

    def bar(cellWidth: Int = 3, cellNum: Int = 3): String = 
        (("+" + "-" * cellWidth) * cellNum) + "+" + sys.props("line.separator")
    
    def cells(row: Int = 3, cellWidth: Int = 3): String = 
        matrix.row(row).map(_.toString).map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2)).mkString("|", "|", "|") + sys.props("line.separator")
    
    def mesh(cellWidth: Int = 3): String = 
        (0 until size).map(cells(_, cellWidth)).mkString(bar(cellWidth, size), bar(cellWidth, size), bar(cellWidth, size))

    def open(x: Int, y: Int, spiel: GameInterface): (FieldInterface, Status) = {
        if (bombenMatrix.cell(y, x) == Symbols.Bomb) {
            spiel.setGameState(Status.Lost)
            //spiel.gameState = Status.Lost
            playerMatrix = playerMatrix.replaceCell(y, x, Symbols.Bomb)
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
