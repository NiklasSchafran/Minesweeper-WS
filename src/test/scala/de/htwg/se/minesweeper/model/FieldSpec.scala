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
        val side = 3

        var playerMatrix = new Matrix(side, Symbols.Covered)
        val fieldstart = new Field(side, Symbols.Covered)
        val emptyMatrix = new Matrix(side, Symbols.Empty)
        val bombenMatrix = emptyMatrix.replaceCell(1, 1, Symbols.Bomb)

        val field = new Field(playerMatrix, bombenMatrix)


        field.bombenMatrix.cell(1, 1) shouldBe Symbols.Bomb
      }
    }
/*
    "a cell is opened" should {
      "reveal empty space and adjacent numbers if no bomb" in {
        val side = 3

        var playerMatrix = new Matrix(side, Symbols.Covered)
        val fieldstart = new Field(side, Symbols.Covered)
        val emptyMatrix = new Matrix(side, Symbols.Empty)

        val field = new Field(playerMatrix, emptyMatrix)

        val game = mock[Game]
        val updateTuple = field.open(1, 1, game)
        val fieldUpdated = updateTuple._1

        fieldUpdated.playerMatrix.cell(1, 1) should not be Symbols.Empty
      }

      "reveal the number of adjacent bombs correctly" in {
        val side = 3

        var playerMatrix = new Matrix(side, Symbols.Covered)
        val fieldstart = new Field(side, Symbols.Covered)
        val emptyMatrix = new Matrix(side, Symbols.Empty)
        val bombenMatrix = emptyMatrix.replaceCell(1, 2, Symbols.Bomb).replaceCell(1, 0, Symbols.Bomb)

        val field = new Field(playerMatrix, bombenMatrix)
        val game = mock[Game]
        val updateTuple = field.open(1, 1, game)
        val fieldUpdated = updateTuple._1

        fieldUpdated.playerMatrix.cell(1, 1) shouldBe Symbols.Two
      }
/*
      "reveal adjacent cells when a 'Zero' cell is opened" in {
        val size = 3
        val bombMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Empty)))
          .replaceCell(0, 0, Symbols.Bomb)
        val playerMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Covered)))
        val game = mock[Game]
        val field = new Field(playerMatrix, bombMatrix)

        val updateTuple = field.open(2, 2, game)
        val fieldUpdated = updateTuple._1

        fieldUpdated.playerMatrix.cell(2, 2) should not be Symbols.Covered
        fieldUpdated.playerMatrix.cell(2, 1) should not be Symbols.Covered
        fieldUpdated.playerMatrix.cell(1, 2) should not be Symbols.Covered
      }

      "not change state when a non-bomb cell is opened" in {
        val size = 3
        val bombMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Empty)))
        val playerMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Covered)))
        val game = mock[Game]
        val field = new Field(playerMatrix, bombMatrix)

        val updateTuple = field.open(1, 1, game)
        val fieldUpdated = updateTuple._1

        fieldUpdated.playerMatrix.cell(1, 1) should not be Symbols.Covered
      }*/
    }
/*
    "Edge cases" should {
      "handle opening a cell on the edge of the field" in {
        val size = 3
        val bombMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Empty)))
        val playerMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Covered)))
        val game = mock[Game]
        val field = new Field(playerMatrix, bombMatrix)

        val updateTuple = field.open(0, 0, game)
        val fieldUpdated = updateTuple._1

        fieldUpdated.playerMatrix.cell(0, 0) should not be Symbols.Covered
      }

      "not crash when opening an already opened cell" in {
        val size = 3
        val bombMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Empty)))
        val playerMatrix = Matrix(Vector.fill(size)(Vector.fill(size)(Symbols.Covered)))
        val game = mock[Game]
        val field = new Field(playerMatrix, bombMatrix)

        val updateTuple1 = field.open(1, 1, game)
        val fieldUpdated1 = updateTuple1._1

        val updateTuple2 = fieldUpdated1.open(1, 1, game)
        val fieldUpdated2 = updateTuple2._1

        fieldUpdated2.playerMatrix.cell(1, 1) should not be Symbols.Covered
      }
    }*/*/
  }
}
