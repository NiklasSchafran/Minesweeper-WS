package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.model.{Field, Symbols}

object FieldFactory {
  def createField(gridSize: Int, symbol: Symbols): Field = {
    new Field(gridSize, symbol)
  }
}