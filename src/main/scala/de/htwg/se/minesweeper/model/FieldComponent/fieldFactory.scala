package de.htwg.se.minesweeper.model.FieldComponent

import de.htwg.se.minesweeper.model.FieldComponent.*
import de.htwg.se.minesweeper.model.FieldComponent.Symbols

object FieldFactory {
  def createField(gridSize: Int, symbol: Symbols): FieldInterface = {
    // Eine Instanz von Field mit leeren Matrizen erstellen
    val emptyMatrix = Matrix(Vector.fill(gridSize, gridSize)(Symbols.Empty))
    val field = Field(emptyMatrix, emptyMatrix)
    
    // Die newField-Methode aufrufen, um das gewünschte Field zu erstellen
    field.newField(gridSize, symbol)
  }
}
