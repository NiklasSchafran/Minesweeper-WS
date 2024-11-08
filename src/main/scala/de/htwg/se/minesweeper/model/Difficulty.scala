package de.htwg.se.minesweeper.model

object Difficulty:
    def setDifficulty() = {
        println("Enter the Difficulty Level")
        println("0 fuer 3x3 und 1er bombe)")
        println("1 fuer 3x3 und 10 bomben")
        println("2 fuer 16x16 und 40 bomben")


        val level = scala.io.StdIn.readInt()


        level match {
            case 0 => (3, 1)
            case 1 => (9, 10)
            case 2 => (16, 40)
            case _ => (9, 10)
        }

    }