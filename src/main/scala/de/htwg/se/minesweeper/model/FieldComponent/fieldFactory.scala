package de.htwg.se.minesweeper.model.FieldComponent

import de.htwg.se.minesweeper.model.FieldComponent._

object FieldFactory {
  def createField(gridSize: Int, symbol: Symbols): FieldInterface = {
    // Matrix explizit als Matrix[Symbols] erstellen
    val emptyMatrix: Matrix[Symbols] = new Matrix(
      Vector.fill(gridSize)(Vector.fill(gridSize)(Empty): Vector[Symbols])
    )
    
    // Field mit den Matrizen erstellen
    val field = Field(emptyMatrix, emptyMatrix)
    
    // newField aufrufen, um das gewünschte Field zu erstellen
    field.newField(gridSize, symbol)
  }
}
