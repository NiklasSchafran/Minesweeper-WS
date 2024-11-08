package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class MatrixSpec extends AnyWordSpec{
    "Datatype Matrix is made from a Vector of Vectors. It is a Matrix:" when {
        "is empty" should {
            "be created by a Dim & Sample cell" in {
                val testMatrix = new Matrix[Symbols](3, Symbols.One)
                testMatrix.size should be (3)
            }
            "Matrix created with Vector of Vectors alone" in {
                val testMatrix2 = Matrix(Vector(Vector(Symbols)))
                testMatrix2.size should be (1)
            }
        }
        "fill Matrix" should {
            val testMatrix3 = new Matrix[String](3, "F")
            val testMatrix4 = new Matrix[String](2, Symbols.Bomb.toString)
            "access it cells" in {
                testMatrix3.cell(0, 0) should be ("F")
                testMatrix4.cell(1, 1) should be ("*")
            }
            "fill with fill function" in {
                val resultMatrix = testMatrix3.fill("S")
                resultMatrix.cell(1, 1) should be ("S")
            }
            "replace cell & return new Matrix" in {
                val resultMatrix2 = testMatrix3.replaceCell(0, 0, "1")
                testMatrix3.cell(0, 0) should be ("F")
                resultMatrix2.cell(0, 0) should be ("1")
            }
        }

    }
}