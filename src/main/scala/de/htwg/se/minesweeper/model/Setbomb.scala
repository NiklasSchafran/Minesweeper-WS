package de.htwg.se.minesweeper.model

import scala.util.Random
import scala.collection.immutable.HashSet
import scala.collection.immutable.Set


object Setbomb:

    def setB(emty1Matrix: Matrix[Symbols], anzahlBomben: Int, x: Int, y: Int): Matrix[Symbols] = {
      
        val verboten = (y, x)
        var BombsMatrix = emty1Matrix
        val sizeM = emty1Matrix.size //-1
        println(sizeM)
        var AnzahlPlaziert : Int = 0
        //var BombSet: Set[(Int, Int)] = Set((y, x))
        val random = new Random()
        while(AnzahlPlaziert < anzahlBomben){
            val x : Int = random.nextInt(sizeM)
            val y : Int = random.nextInt(sizeM)
            val tupel = (y, x)

            if(BombsMatrix.cell(y, x) != Symbols.Bomb && tupel != verboten){  //!(BombSet.contains(tupel))
                BombsMatrix = BombsMatrix.replaceCell(y, x, Symbols.Bomb)
                AnzahlPlaziert += 1
            }
        }
        return BombsMatrix
    }