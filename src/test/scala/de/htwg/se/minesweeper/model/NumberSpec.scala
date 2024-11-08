package de.htwg.se.minesweeper.model

import java.io.{ByteArrayOutputStream, PrintStream}
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.Number.Num

class NumberSpec extends AnyWordSpec{
    "Number" should{

        val side3 = 3
        var testMatrix = new Matrix[Symbols](side3, Symbols.Empty)
        val testBombMatrix = testMatrix.replaceCell(1, 1, Symbols.Bomb)
        var testPlayerMatrix = new Matrix(side3, Symbols.Covered)
        var testAnzahlcoverd : Int = 0
        
        "have a Mine count" in{
            val testTupelN = Num(0, 0, testBombMatrix, testPlayerMatrix, testAnzahlcoverd)
                testPlayerMatrix = testTupelN._2
                testAnzahlcoverd = testTupelN._1

            testAnzahlcoverd should be (1)

            val testTupelN2 = Num(0, 1, testBombMatrix, testPlayerMatrix, testAnzahlcoverd)
                testPlayerMatrix = testTupelN2._2
                testAnzahlcoverd = testTupelN2._1

                testAnzahlcoverd should be (2)

            val testTupelN3 = Num(2, 2, testBombMatrix, testPlayerMatrix, testAnzahlcoverd)
                testPlayerMatrix = testTupelN3._2
                testAnzahlcoverd = testTupelN3._1



            testAnzahlcoverd should be (3)
            testPlayerMatrix.cell(0,0) should be (Symbols.One)
            testPlayerMatrix.cell(1,0) should be (Symbols.One)
            testPlayerMatrix.cell(2,2) should be (Symbols.One)
            testPlayerMatrix.cell(1,1) should be (Symbols.Covered)
            testBombMatrix.cell(1,1) should be (Symbols.Bomb)


        }
    }
}