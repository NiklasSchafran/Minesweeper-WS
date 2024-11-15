package de.htwg.se.minesweeper.model

import scala.io.StdIn.readLine
import scala.util.Random
import de.htwg.se.minesweeper.model.Field


case class Game(state: Status):

    var anzahBomben = 0
    var side = 0
    var gameState = Status.Playing


    def setDifficulty() = {
            println("Enter the Difficulty Level")
            println("0 fuer 3x3 und 1er bombe)")
            println("1 fuer 3x3 und 6 bomben")
            println("2 fuer 16x16 und 40 bomben")


            val level = scala.io.StdIn.readInt()


            level match {
                case 0 => (3, 1)
                case 1 => (9, 6)
                case 2 => (16, 40)
                case _ => (9, 6)
            }

    }

    


    // Function for 1st Move
    def premierMove(x: Int, y: Int, field: Field, game: Game): Field = {
      // shows covered playField
        var anzahlcoverd = 1 // noch ändern
        var playerMatrix = new Matrix(side, Symbols.Covered)
        val fieldstart = new Field(side, Symbols.Covered)
        val emptyMatrix = new Matrix(side, Symbols.Empty)
        val bombenMatrix = setB(emptyMatrix, anzahBomben, x, y)

        val Bombstart = new Field(bombenMatrix)
        print(Bombstart.toString())
        
        playerMatrix = Num(x, y, bombenMatrix, playerMatrix)

        val matrixAfterMove = new Field(playerMatrix, bombenMatrix)
        matrixAfterMove
    }

  
    def inArea(x: Int, y: Int, side: Int): Boolean = {x >= 0 && x <= side && y >= 0 && y <= side}



    // initialises hiddenMatrix witch is initialises with bombs with adjacent Numbers
    def Num(x : Int, y : Int, bMatrix: Matrix[Symbols], pMatrix: Matrix[Symbols]): Matrix[Symbols] = {


        var tmpMatrix = pMatrix
        val si = bMatrix.size - 1

        if(!(inArea(x, y, si)) || pMatrix.cell(y, x) != Symbols.Covered){
            return pMatrix
        }        
        var minesFound = 0
        if(isBomb(x+1, y+1, bMatrix)){minesFound += 1}
        if(isBomb(x, y+1, bMatrix)){minesFound+= 1}
        if(isBomb(x-1, y+1, bMatrix)){minesFound+= 1}
        if(isBomb(x+1, y, bMatrix)){minesFound+= 1}
        if(isBomb(x-1, y, bMatrix)){minesFound+= 1}
        if(isBomb(x+1, y-1, bMatrix)){minesFound+= 1}
        if(isBomb(x, y-1, bMatrix)){minesFound+= 1}
        if(isBomb(x-1, y-1, bMatrix)){minesFound+= 1}

        if(minesFound == 0){
            tmpMatrix = tmpMatrix.replaceCell(y, x, Symbols.Empty)
            
            if(inArea(x+1, y+1, si))
                tmpMatrix = Num(x+1, y+1, bMatrix, tmpMatrix)
            if(inArea(x, y+1, si))
                tmpMatrix = Num(x, y+1, bMatrix, tmpMatrix)
            if(inArea(x-1, y+1, si))
                tmpMatrix = Num(x-1, y+1, bMatrix, tmpMatrix)
            if(inArea(x+1, y, si))
                tmpMatrix = Num(x+1, y, bMatrix, tmpMatrix)
            if(inArea(x-1, y, si))
                tmpMatrix = Num(x-1, y, bMatrix, tmpMatrix)
            if(inArea(x+1, y-1, si))
                tmpMatrix = Num(x+1, y-1, bMatrix, tmpMatrix)
            if(inArea(x, y-1, si))
                tmpMatrix = Num(x, y-1, bMatrix, tmpMatrix)
            if(inArea(x-1, y-1, si))
                tmpMatrix = Num(x-1, y-1, bMatrix, tmpMatrix)
            
            return tmpMatrix   
        }else{
            val symb = minesFound match {
                            case 0 => Symbols.Zero
                            case 1 => Symbols.One
                            case 2 => Symbols.Two
                            case 3 => Symbols.Three
                            case 4 => Symbols.Four
                            case 5 => Symbols.Five
                            case 6 => Symbols.Six
                            case 7 => Symbols.Seven
                            case 8 => Symbols.Eight
                        }
            tmpMatrix = tmpMatrix.replaceCell(y, x, symb)
        }
        return tmpMatrix
    }

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

    def isBomb(x: Int, y: Int, m: Matrix[Symbols]): Boolean = {
        val si = m.size-1
        if(inArea(x, y,si)){
            if(m.cell(y, x) == Symbols.Bomb){
                return true
            }
        }
        return false
    }

    def checkGameState(realgame: Game) =
      if(this.gameState == Status.Won) println("you just won!!!") else if (this.gameState == Status.Lost) println("you just Lost!!!") else print("")