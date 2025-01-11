package de.htwg.se.minesweeper.model.FieldComponent

import de.htwg.se.minesweeper.model.FieldComponent.*
import de.htwg.se.minesweeper.model.FieldComponent.Symbols

object FieldFactory{
  def createField(gridSize: Int, symbol: Symbols): Field = {
    new Field(gridSize, symbol)
  }
}

