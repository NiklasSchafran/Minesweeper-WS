package de.htwg.se.minesweeper.model.GameComponent

// Status for Game
sealed trait Status

object Status {
  case object Playing extends Status
  case object Won extends Status
  case object Lost extends Status
}
