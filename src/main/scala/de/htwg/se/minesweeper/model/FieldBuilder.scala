package de.htwg.se.minesweeper.model

import scala.io.StdIn.readLine
import scala.util.Random
import de.htwg.se.minesweeper.model.Field
import de.htwg.se.minesweeper.difficulty.DifficultyStrategy


class FieldBuilder {
  private var size: Int = 0
  private var playerMatrix: Option[Matrix[Symbols]] = None
  private var bombMatrix: Option[Matrix[Symbols]] = None
  private var x: Int = 0
  private var y: Int = 0
  private var bombCount: Int = 0

  def withSize(size: Int): FieldBuilder = {
    this.size = size
    this
  }

  def withInitialPosition(x: Int, y: Int): FieldBuilder = {
    this.x = x
    this.y = y
    this
  }

  def withBombCount(anzahlBomben: Int): FieldBuilder = {
    this.bombCount = anzahlBomben
    this
  }

  def addField(game: Game): FieldBuilder = {
    this.playerMatrix = Some(new Matrix(size, Symbols.Covered))

    val emptyMatrix = new Matrix(size, Symbols.Empty)
    this.bombMatrix = Some(game.setB(emptyMatrix, bombCount, x, y))
    this.playerMatrix = this.playerMatrix.map(pm => game.Num(x, y, this.bombMatrix.get, pm))
    this
  }

  def build(): Field = {
    require(size > 0, "Field size must be greater than 0")
    require(playerMatrix.isDefined, "Player matrix must be initialized")
    require(bombMatrix.isDefined, "Bomb matrix must be initialized")

    new Field(playerMatrix.get, bombMatrix.get)
  }
}