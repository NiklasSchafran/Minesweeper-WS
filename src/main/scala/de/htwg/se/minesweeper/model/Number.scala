package de.htwg.se.minesweeper.model
import de.htwg.se.minesweeper.model.Area.inArea
import de.htwg.se.minesweeper.model.Checkifbomb.isBomb
import de.htwg.se.minesweeper.model.Symbols
import de.htwg.se.minesweeper.model.NoNumber.noNum
import scala.collection.immutable.Set



object Number:

    
    def Num(x : Int, y : Int, bMatrix: Matrix[Symbols], pMatrix: Matrix[Symbols] , anzahlcNum: Int): (Int, Matrix[Symbols]) = {


    var tmpMatrix = pMatrix
    val si = bMatrix.size - 1
    var anzahlcoverd = anzahlcNum


    if(!(inArea(x, y, si)) || pMatrix.cell(y, x) != Symbols.Covered){
        return(anzahlcoverd,pMatrix)
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
        anzahlcoverd += 1
        val resTupleTmp = noNum(x, y, bMatrix, tmpMatrix, anzahlcoverd)
            anzahlcoverd = resTupleTmp._1
            tmpMatrix = resTupleTmp._2

        
        return(anzahlcoverd, tmpMatrix)

            
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
        anzahlcoverd += 1
    }
    return(anzahlcoverd,tmpMatrix)
}