import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.minesweeper.model.{Matrix, Symbols}

class MatrixSpec extends AnyWordSpec {

  "A Matrix" when {

    "empty" should {
      "be created with a specified dimension and a filling element" in {
        val testMatrix = new Matrix[Symbols](3, Symbols.One)
        testMatrix.size should be(3)
        // Additional checks to ensure all elements are Symbols.One
      }

      "be created from a Vector of Vectors" in {
        val testMatrix2 = Matrix(Vector(Vector(Symbols.One)))
        testMatrix2.size should be(1)
        // Additional checks for the content of testMatrix2
      }
    }

    "filled with elements" should {
      val testMatrix3 = new Matrix[String](3, "F")

      "allow access to its cells" in {
        testMatrix3.cell(0, 0) should be("F")
        // Additional checks for other cells
      }

      "be fillable with a new element" in {
        val resultMatrix = testMatrix3.fill("S")
        resultMatrix.cell(1, 1) should be("S")
        // Check immutability - original matrix should remain unchanged
        testMatrix3.cell(1, 1) should be("F")
      }

      "allow replacing a cell and return a new Matrix" in {
        val resultMatrix2 = testMatrix3.replaceCell(0, 0, "1")
        resultMatrix2.cell(0, 0) should be("1")
        // Check immutability - original matrix should remain unchanged
        testMatrix3.cell(0, 0) should be("F")
      }
    }

    "created from a Vector of Vectors" should {
      val vectorMatrix = Matrix(Vector(Vector(1, 2, 3), Vector(4, 5, 6), Vector(7, 8, 9)))

      "return the correct row when accessed" in {
        vectorMatrix.row(1) should be(Vector(4, 5, 6))
        // Additional checks for other rows
      }
    }

  }
}