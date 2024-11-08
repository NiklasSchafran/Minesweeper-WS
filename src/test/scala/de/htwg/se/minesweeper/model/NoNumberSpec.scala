package de.htwg.se.minesweeper.model

import java.io.{ByteArrayOutputStream, PrintStream}
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.Number.Num

class NoNumberSpec extends AnyWordSpec{
    "NoNum" should{

        val side3 = 3
        var testMatrix = new Matrix[Symbols](side3, Symbols.Empty)
        val testBombMatrix = testMatrix.replaceCell(0, 0, Symbols.Bomb)
        var testPlayerMatrix = new Matrix(side3, Symbols.Covered)
        var testAnzahlcoverd : Int = 0
        
        "have a Mine count" in{
            val testTupelN = Num(2, 2, testBombMatrix, testPlayerMatrix, testAnzahlcoverd)
                testPlayerMatrix = testTupelN._2
                testAnzahlcoverd = testTupelN._1

            testAnzahlcoverd should be (8)



            testPlayerMatrix.cell(0,0) should be (Symbols.Covered)
            testPlayerMatrix.cell(1,0) should be (Symbols.One)
            testPlayerMatrix.cell(0,1) should be (Symbols.One)
            testPlayerMatrix.cell(1,1) should be (Symbols.One)
            testPlayerMatrix.cell(2,2) should be (Symbols.Empty)
            testPlayerMatrix.cell(1,2) should be (Symbols.Empty)

            testBombMatrix.cell(0,0) should be (Symbols.Bomb)


        }
    }
}