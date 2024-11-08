package de.htwg.se.minesweeper.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import Difficulty.setDifficulty

class DifficultySpec extends AnyWordSpec{
    "The setDifficulty method" should {
        "return a tuple representing the chosen difficulty" in {
            val in = new java.io.ByteArrayInputStream("0\n".getBytes)
            Console.withIn(in){
                val (side, bombs) = Difficulty.setDifficulty()
                side should be (3)
                bombs should be (1)
            }
        
        }

        "return the beginner difficulty when input is 1" in {
            val in = new java.io.ByteArrayInputStream("1\n".getBytes)
            Console.withIn(in){
                val (side, bombs) = Difficulty.setDifficulty()
                side should be (9)
                bombs should be (10)

            }
        }

        
        "return the intermediate difficulty when input is 2" in {
            val in = new java.io.ByteArrayInputStream("2\n".getBytes)
            Console.withIn(in){
                val (side, bombs) = Difficulty.setDifficulty()
                side should be (16)
                bombs should be (40)
            }
        }

        "return the beginner difficulty by default when input is not recognized" in {
            val in = new java.io.ByteArrayInputStream("33\n".getBytes)
            Console.withIn(in){
                val (side, bombs) = Difficulty.setDifficulty()
                side should be (9)
                bombs should be (10)
            }
        }
    }
}

/* class DifficultySpec extends AnyWordSpec{
    "Difficulty" should {
        "by Default be Super Easy" in {
            val difficultyTest = chooseDifficulty()
            difficultyTest should be ((3, 1))
        }
    }
} */