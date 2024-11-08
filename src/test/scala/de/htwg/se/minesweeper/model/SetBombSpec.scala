package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import Setbomb.setB

class SetBombSpec extends AnyWordSpec{
    "Bombs" should {
        val side = 3
        val bombs = 8
        val testMineMatrix = new Matrix(side, Symbols.Empty)
        
        "be initialised random with the given amount of bombs" in {
            val testMineMatrixResult = setB(testMineMatrix, bombs, 1, 1)
            testMineMatrixResult.cell(0, 0) should be (Symbols.Bomb)
            testMineMatrixResult.cell(1, 0) should be (Symbols.Bomb)
            testMineMatrixResult.cell(0, 1) should be (Symbols.Bomb)
            testMineMatrixResult.cell(2, 2) should be (Symbols.Bomb)
        }
    }
}