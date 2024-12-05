package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.{Field, Move}
import de.htwg.se.minesweeper.util.Observable
import de.htwg.se.minesweeper.model.Game


case class Controller(var field: Field, game: Game) extends Observable:
    
    def firstMove(x: Int, y: Int, game: Game) = 
        field = game.premierMove(x, y, field, game)
        notifyObservers

    def uncoverField(x: Int , y: Int, game: Game) = 
        
        val updateTupel = field.open(x, y, game)
        field = updateTupel._1
        game.gameState = updateTupel._2
        notifyObservers
    
    
    override def toString = field.toString
