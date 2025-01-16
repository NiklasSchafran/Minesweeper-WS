package de.htwg.se.minesweeper.difficulty
import de.htwg.se.minesweeper.model.GameComponent.Game

trait DifficultyStrategy {
  def setDifficulty(game: Game): Unit
}

class EasyDifficulty extends DifficultyStrategy {
  override def setDifficulty(game: Game): Unit = {
    game.side = 3
    game.anzahBomben = 1
  }
}

class MediumDifficulty extends DifficultyStrategy {
  override def setDifficulty(game: Game): Unit = {
    game.side = 9
    game.anzahBomben = 6
  }
}

class HardDifficulty extends DifficultyStrategy {
  override def setDifficulty(game: Game): Unit = {
    game.side = 16
    game.anzahBomben = 40
  }
}