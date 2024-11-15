package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import java.io.ByteArrayOutputStream

class GameSpec extends AnyWordSpec {
    
    "The setDifficulty method" should {
        val testGame = new Game(Status.Playing)
        "return a tuple representing the chosen difficulty" in {
            val in = new java.io.ByteArrayInputStream("0\n".getBytes)
            Console.withIn(in){
                val (side, bombs) = testGame.setDifficulty()
                side should be (3)
                bombs should be (1)
            }
        }

        "return the beginner difficulty when input is 1" in {
            val in = new java.io.ByteArrayInputStream("1\n".getBytes)
            Console.withIn(in) {
                val (side, bombs) = testGame.setDifficulty()
                side should be (9)
                bombs should be (6)
            }
        }

        "return the intermediate difficulty when input is 2" in {
            val in = new java.io.ByteArrayInputStream("2\n".getBytes)
            Console.withIn(in){
                val (side, bombs) = testGame.setDifficulty()
                side should be (16)
                bombs should be (40)
            }
        }


        "return the beginner difficulty by default when input is not recognized" in{
            val in = new java.io.ByteArrayInputStream("33\n".getBytes)
            Console.withIn(in){
                val (side, bombs) = testGame.setDifficulty()
                side should be (9)
                bombs should be (6)
            }
        }
    }

/*
    "Covered" should {
        val testGame4 = new Game(Status.Playing)
        val side2 = 3
        val visibleMatrixTest = new Matrix(side2, Symbols.Covered)
        "count just all the covered Martix Cells" in {
            val calcVisibleMatrixTest = testGame4.calcCovered(visibleMatrixTest)
            calcVisibleMatrixTest should be (9)

            val testRange = 0 to visibleMatrixTest.size-1
            testRange should be (scala.collection.immutable.Range.inclusive(0, 2))
        }
    }

*/
    "Minecheck" should{
        val testGame6 = new Game(Status.Playing)
        val side4 = 3
        val mineCheckMatrix = new Matrix(side4, Symbols.Empty)
        val mineCheckMatrix2 = mineCheckMatrix.replaceCell(1, 1, Symbols.Bomb)

        "check if cell at location x,y has bomb or not" in{
            val boolCheckIsMine = testGame6.isBomb(1, 1, mineCheckMatrix2)
            val boolCheckIsMine2 = testGame6.isBomb(0, 0, mineCheckMatrix2)
            boolCheckIsMine should be (true)
            boolCheckIsMine2 should be (false)
        }
    }

    "Bombs" should {

        val testGame8 = new Game(Status.Playing)
        val side = 3
        val bombs = 8
        val testMineMatrix = new Matrix(side, Symbols.Empty)
        
        "be initialised random with the given amount of bombs" in {
            val testMineMatrixResult = testGame8.setB(testMineMatrix, bombs, 1, 1)
            testMineMatrixResult.cell(0, 0) should be (Symbols.Bomb)
            testMineMatrixResult.cell(1, 0) should be (Symbols.Bomb)
            testMineMatrixResult.cell(0, 1) should be (Symbols.Bomb)
            testMineMatrixResult.cell(2, 2) should be (Symbols.Bomb)
        }
    }

    "Number" should{

        val testGame9 = new Game(Status.Playing)
        val side3 = 3
        var testMatrix = new Matrix[Symbols](side3, Symbols.Empty)
        val testBombMatrix = testMatrix.replaceCell(1, 1, Symbols.Bomb)
        var testPlayerMatrix = new Matrix(side3, Symbols.Covered)
        
        "have a Mine count" in{
            testPlayerMatrix = testGame9.Num(0, 0, testBombMatrix, testPlayerMatrix)

                testPlayerMatrix.cell(0,0) should be (Symbols.One)
                testPlayerMatrix.cell(1,0) should be (Symbols.Covered)
                testPlayerMatrix.cell(2,2) should be (Symbols.Covered)
                testPlayerMatrix.cell(1,1) should be (Symbols.Covered)
                testBombMatrix.cell(1,1) should be (Symbols.Bomb)

            testPlayerMatrix = testGame9.Num(0, 1, testBombMatrix, testPlayerMatrix)
                
                testPlayerMatrix.cell(0,0) should be (Symbols.One)
                testPlayerMatrix.cell(1,0) should be (Symbols.One)
                testPlayerMatrix.cell(2,2) should be (Symbols.Covered)
                testPlayerMatrix.cell(1,1) should be (Symbols.Covered)
                testBombMatrix.cell(1,1) should be (Symbols.Bomb)


            testPlayerMatrix = testGame9.Num(2, 2, testBombMatrix, testPlayerMatrix)
                

                testPlayerMatrix.cell(0,0) should be (Symbols.One)
                testPlayerMatrix.cell(1,0) should be (Symbols.One)
                testPlayerMatrix.cell(2,2) should be (Symbols.One)
                testPlayerMatrix.cell(1,1) should be (Symbols.Covered)
                testBombMatrix.cell(1,1) should be (Symbols.Bomb)


        }
    }

    "NoNum" should{

            val side3 = 3
            val testGame10 = new Game(Status.Playing)
            var testMatrix = new Matrix[Symbols](side3, Symbols.Empty)
            val testBombMatrix = testMatrix.replaceCell(0, 0, Symbols.Bomb)
            var testPlayerMatrix = new Matrix(side3, Symbols.Covered)
            var testAnzahlcoverd : Int = 0
            
            "have a Mine count" in{
                testPlayerMatrix = testGame10.Num(2, 2, testBombMatrix, testPlayerMatrix)


                testPlayerMatrix.cell(0,0) should be (Symbols.Covered)
                testPlayerMatrix.cell(1,0) should be (Symbols.One)
                testPlayerMatrix.cell(0,1) should be (Symbols.One)
                testPlayerMatrix.cell(1,1) should be (Symbols.One)
                testPlayerMatrix.cell(2,2) should be (Symbols.Empty)
                testPlayerMatrix.cell(1,2) should be (Symbols.Empty)

                testBombMatrix.cell(0,0) should be (Symbols.Bomb)


            }
    }

    "inArea" should{

            val side3 = 2
            val testGame11 = new Game(Status.Playing)
            var testMatrix = new Matrix[Symbols](side3+1, Symbols.Empty)
            val testBombMatrix = testMatrix.replaceCell(0, 0, Symbols.Bomb)
            var testPlayerMatrix = new Matrix(side3, Symbols.Covered)
            var testAnzahlcoverd : Int = 0
            "check if xy is in Area" in{
                testGame11.inArea(0, 0, side3) should be (true)
                testGame11.inArea(1, 1, side3) should be (true)
                testGame11.inArea(2, 2, side3) should be (true)
                testGame11.inArea(2, 0, side3) should be (true)
                testGame11.inArea(3, 0, side3) should be (false)
                testGame11.inArea(3, 3, side3) should be (false)
                testGame11.inArea(2, 4, side3) should be (false)
                testGame11.inArea(-1, 0, side3) should be (false)
            }
    }

    "Function premierMove" should {
        val testGame12 = new Game(Status.Playing)
        testGame12.anzahBomben = 2
        testGame12.side = 3
        val testSide = 3
        val testField = new Field(testSide, Symbols.Covered)
   
        "return new Field after 1st move" in{
            val testFieldAfterMove = testGame12.premierMove(1, 1, testField, testGame12)
            testFieldAfterMove should not be (testField)
            testFieldAfterMove.matrix.cell(1, 1) should not be (Symbols.Covered)
        }
    
    }
    /*
        "Function decide" should{
        val testGame13 = new Game(Status.Lost)
        testGame13.gameState = Status.Lost
        val testSide13 = 3
 
        val testHid13 = new Matrix(testSide13, Symbols.Empty)
        val testBomb = testHid13.replaceCell(1, 1, Symbols.Bomb)
        val testAdjacent = testGame13.initializeAdjacentNumbers(testBomb)

        "return adjacent matrix which is different from input matrix when game is lost" in{
            val testResult13 = testGame13.decide(1, 1, testGame13, testAdjacent)

            testResult13 should not be (testAdjacent)
        }
    }
*/
}