package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.FieldComponent.*//{Field, Game}
import de.htwg.se.minesweeper.model.GameComponent.*
import de.htwg.se.minesweeper.difficulty.DifficultyStrategy
import de.htwg.se.minesweeper.util.Observable

trait ControllerInterface extends Observable {
  def field: FieldInterface
  def game: Game
  def bFirstMove: Boolean
  def bFirstMove_=(value: Boolean): Unit
  def firstMove(x: Int, y: Int, game: Game): Unit
  def uncoverField(x: Int, y: Int, game: Game): Unit
  def setDifficulty(strategy: DifficultyStrategy): Unit
  def executeCommand(command: Command): Unit
  def undo(): Unit
}
