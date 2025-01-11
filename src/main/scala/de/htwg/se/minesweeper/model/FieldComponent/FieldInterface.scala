package de.htwg.se.minesweeper.model.FieldComponent
import de.htwg.se.minesweeper.difficulty.DifficultyStrategy
import de.htwg.se.minesweeper.model.GameComponent.*
import de.htwg.se.minesweeper.model.FieldComponent.*

trait FieldInterface {
    val size: Int
    var playerMatrix: Matrix[Symbols]
    var bombenMatrix: Matrix[Symbols]
    def newField(size: Int, filling: Symbols): FieldInterface
    def mesh(cellWidth: Int = 3): String
    def open(x: Int, y: Int, spiel: GameInterface): (FieldInterface, Status)
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

trait FieldBuilderTrait {
  def withSize(size: Int): FieldBuilderTrait
  def withInitialPosition(x: Int, y: Int): FieldBuilderTrait
  def withBombCount(anzahlBomben: Int): FieldBuilderTrait
  def addField(game: Game): FieldBuilderTrait
  def build(): Field
}

trait FieldFactoryTrait {
  def createField(gridSize: Int, symbol: Symbols): Field
}

trait SymbolsTrait {
  def toString: String
}