package de.htwg.se.minesweeper.model.GameComponent
import de.htwg.se.minesweeper.difficulty.DifficultyStrategy
import de.htwg.se.minesweeper.model.FieldComponent.*

/*
trait GameInterface {
    def setDifficultyStrategy(strategy: DifficultyStrategy): Unit
    def premierMove(x: Int, y: Int, field: Field, game: Game): Field
    def inArea(x: Int, y: Int, side: Int): Boolean
    def Num(x: Int, y: Int, bMatrix: Matrix[Symbols], pMatrix: Matrix[Symbols]): Matrix[Symbols]
    def setB(emptyMatrix: Matrix[Symbols], anzahlBomben: Int, x: Int, y: Int): Matrix[Symbols]
    def isBomb(x: Int, y: Int, m: Matrix[Symbols]): Boolean
}
*/
trait GameInterface {

    def setDifficultyStrategy(strategy: DifficultyStrategy): Unit
    def premierMove(x: Int, y: Int, field: Field, game: Game): Field
    def inArea(x: Int, y: Int, side: Int): Boolean
    def Num(x: Int, y: Int, bMatrix: Matrix[Symbols], pMatrix: Matrix[Symbols]): Matrix[Symbols]
    def setB(emptyMatrix: Matrix[Symbols], anzahlBomben: Int, x: Int, y: Int): Matrix[Symbols]
    def isBomb(x: Int, y: Int, m: Matrix[Symbols]): Boolean
}
