package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class CheckifbombSpec extends AnyWordSpec{
    "isBomb" should {
        val side = 3
        val mineCheckMatrix = new Matrix(side, Symbols.Empty)
        val mineCheckMatrix2 = mineCheckMatrix.replaceCell(1, 1, Symbols.Bomb)
        "check if cell at location x,y has bomb or not" in {
            val boolCheckIsMine = Checkifbomb.isBomb(1, 1, mineCheckMatrix2)
            val boolCheckIsMine2 = Checkifbomb.isBomb(0, 0, mineCheckMatrix2)
            boolCheckIsMine should be (true)
            boolCheckIsMine2 should be (false)

        }
    }
}