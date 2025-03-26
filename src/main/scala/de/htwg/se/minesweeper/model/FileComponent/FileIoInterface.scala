package de.htwg.se.minesweeper.model.FileComponent


import de.htwg.se.minesweeper.model.FieldComponent.*

trait FileIOInterface {
  def load: FieldInterface
  def save(field: FieldInterface): Unit
}