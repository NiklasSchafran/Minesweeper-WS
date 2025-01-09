package de.htwg.se.minesweeper.model
import de.htwg.se.minesweeper.difficulty.DifficultyStrategy


trait GameInterface {
    def setDifficultyStrategy(strategy: DifficultyStrategy): Unit
    def premierMove(x: Int, y: Int, field: Field, game: Game): Field
    def inArea(x: Int, y: Int, side: Int): Boolean
    def Num(x: Int, y: Int, bMatrix: Matrix[Symbols], pMatrix: Matrix[Symbols]): Matrix[Symbols]
    def setB(emptyMatrix: Matrix[Symbols], anzahlBomben: Int, x: Int, y: Int): Matrix[Symbols]
    def isBomb(x: Int, y: Int, m: Matrix[Symbols]): Boolean
}

trait FieldInterface {
    val size: Int
    def mesh(cellWidth: Int = 3): String
    def open(x: Int, y: Int, spiel: Game): (Field, Status)
    def cell(row: Int, col: Int): Symbols
    override def toString(): String
}

trait MatrixInterface[T] {
    val size: Int
    def cell(row: Int, col: Int): T
    def row(row: Int): Vector[T]
    def fill(filling: T): Matrix[T]
    def replaceCell(row: Int, col: Int, cell: T): Matrix[T]
}
