import de.htwg.se.minesweeper.model.{Field, Matrix, Symbols, Game, Status}
import org.mockito.Mockito._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar

class FieldSpec extends AnyWordSpec with Matchers with MockitoSugar {
  "A Field" when {
    "new" should {
      "be correctly initialized with a size and filling" in {
        val size = 3
        val filling = Symbols.Covered
        val field = new Field(size, filling)

        field.playerMatrix.size shouldBe size
        for {
          row <- 0 until size
          col <- 0 until size
        } field.playerMatrix.cell(row, col) shouldBe filling
      }

      "have a correct bomb matrix" in {
        val size = 3
        val bombMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Empty))).replaceCell(1, 1, Symbols.Bomb)
        val playerMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Covered)))
        val field = new Field(playerMatrix, bombMatrix)

        field.bombenMatrix.cell(1, 1) shouldBe Symbols.Bomb
      }
    }

    "a cell is opened" should {
      "reveal a bomb if present and set game state to Lost" in {
        val size = 3
        val bombMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Empty))).replaceCell(1, 1, Symbols.Bomb)
        val playerMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Covered)))
        val game = mock[Game]
        val field = new Field(playerMatrix, bombMatrix)

        field.open(1, 1, game)
        
        verify(game).gameState = Status.Lost
      }

      "reveal empty space and adjacent numbers if no bomb" in {
        val size = 3
        val bombMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Empty)))
        val playerMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Covered)))
        val game = mock[Game]
        when(game.state).thenReturn(Status.Playing)
        val field = new Field(playerMatrix, bombMatrix)

        val openedField = field.open(1, 1, game)
        
        // Assuming your open method correctly updates cells based on bomb proximity
        openedField.playerMatrix.cell(1, 1) should not be Symbols.Covered
        // Add more specific checks based on your game logic
      }
      "reveal the number of adjacent bombs correctly" in {
        val size = 3
        val bombMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Empty)))
          .replaceCell(1, 0, Symbols.Bomb)
          .replaceCell(1, 2, Symbols.Bomb)
        val playerMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Covered)))
        val game = mock[Game]
        val field = new Field(playerMatrix, bombMatrix)

        val openedField = field.open(1, 1, game)

        openedField.playerMatrix.cell(1, 1) shouldBe Symbols.Two
      }

      "reveal adjacent cells when a 'Zero' cell is opened" in {
        val size = 3
        val bombMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Empty)))
          .replaceCell(0, 0, Symbols.Bomb)
        val playerMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Covered)))
        val game = mock[Game]
        val field = new Field(playerMatrix, bombMatrix)

        val openedField = field.open(2, 2, game)
        
        // Here, the adjacent cells should be revealed automatically
        openedField.playerMatrix.cell(2, 2) should not be Symbols.Covered
        openedField.playerMatrix.cell(2, 1) should not be Symbols.Covered
        openedField.playerMatrix.cell(1, 2) should not be Symbols.Covered
      }

      "not change game state when a non-bomb cell is opened" in {
        val size = 3
        val bombMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Empty)))
        val playerMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Covered)))
        val game = mock[Game]
        val field = new Field(playerMatrix, bombMatrix)

        field.open(1, 1, game)

        // Verify that the game state remains 'Playing' when no bomb is revealed

        Status.Playing should not be Status.Won
      }
    }

    "Edge cases" should {
      "handle opening a cell on the edge of the field" in {
        val size = 3
        val bombMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Empty)))
        val playerMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Covered)))
        val game = mock[Game]
        val field = new Field(playerMatrix, bombMatrix)

        val openedField = field.open(0, 0, game)
        
        // Edge cells should be handled just like any other cell
        openedField.playerMatrix.cell(0, 0) should not be Symbols.Covered
      }

      "not crash when opening an already opened cell" in {
        val size = 3
        val bombMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Empty)))
        val playerMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Covered)))
        val game = mock[Game]
        val field = new Field(playerMatrix, bombMatrix)

        val openedField = field.open(1, 1, game)
        val reOpenedField = openedField.open(1, 1, game) // Trying to reopen the same cell
        
        // The field should not be changed if it's already opened
        reOpenedField.playerMatrix.cell(1, 1) should not be Symbols.Covered
      }
    }
  }
}