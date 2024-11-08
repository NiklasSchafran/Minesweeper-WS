package de.htwg.se.minesweeper.model
import de.htwg.se.minesweeper.model.Area.inArea


object Checkifbomb:
    
    def isBomb(x: Int, y: Int, m: Matrix[Symbols]): Boolean = {
        val si = m.size-1
        if(inArea(x, y,si)){
            if(m.cell(y, x) == Symbols.Bomb){
                return true
            }
        }
        return false
    }
