package de.htwg.se.minesweeper

import model.Field
import model.Matrix
import model.Symbols
import model.Difficulty.setDifficulty
import model.Area.inArea
import model.Checkifbomb.isBomb
import model.Status
import model.Number.Num
import model.Setbomb.setB
import scala.compiletime.ops.int
import java.awt.Taskbar.State

  object Main {
  def main(args: Array[String]): Unit = {
    println("Welcome to Minesweeper")

  
    val difficulty = setDifficulty()
    val side = difficulty(0)
    val anzahBomben = difficulty(1)
    var anzahlcoverd : Int = 0


    var playerMatrix = new Matrix(side, Symbols.Covered)
    val fieldstart = new Field(playerMatrix)
    print(fieldstart.toString())

    var game = Status.Playing


    printf("schreibe einen Wert fuer x: ")
    var x = scala.io.StdIn.readInt()
    printf("\nschreibe einen Wert fuer y: ")
    var y = scala.io.StdIn.readInt()

    if (!inArea(x, y, side)){ 
            println("tupel ist nicht im bereich123")
    }

    val emptyMatrix = new Matrix(side, Symbols.Empty)
    //val bombenMatrix = intitializeBombs(anzahBomben, x, y, emptyMatrix) noch ändern !!!
    val bombenMatrix = setB(emptyMatrix, anzahBomben, x, y)
    val Bombstart = new Field(bombenMatrix)
    print(Bombstart.toString())


          

    val resTuple = Num(x, y, bombenMatrix, playerMatrix, anzahlcoverd)
    anzahlcoverd = resTuple._1
    playerMatrix = resTuple._2

    val matAfterFirstMove = new Field(playerMatrix)
        print(matAfterFirstMove.toString())
  




    while(game != Status.Lost && game != Status.Won){ //game und status stuff noch ändern!!!
        printf("schreibe einen Wert fuer x: ")
        x = scala.io.StdIn.readInt()
        printf("\nschreibe einen Wert fuer y: ")
        y = scala.io.StdIn.readInt()

        if (!inArea(x, y, side)){ 
            println("tupel ist nicht im bereich")
          
        }else if(isBomb(x, y, bombenMatrix)){
                game = Status.Lost
                val LastMove = new Field(bombenMatrix)
                print(LastMove.toString())
                printf("Du hast eine Bombe getroffen")
                //show hiddenMatrix

        }else{
        //var aSet: Set[(x:Int, y:Int)] = new HashSet[(x:Int, y:Int)]()
        //val tupleSet: Set[(Int, Int)] = Set((1, 2), (3, 4), (5, 6))


        val resTuple1 = Num(x, y, bombenMatrix, playerMatrix, anzahlcoverd)
        anzahlcoverd = resTuple1._1
        playerMatrix = resTuple1._2

        val matAfterMove = new Field(playerMatrix)
        print(matAfterMove.toString())
        
        if(anzahlcoverd + anzahBomben == side* side){
          Status.Won
          printf("DU hast gewonnen!!!!!!\n")
        }
  
        }
      
    }
      
  }
}