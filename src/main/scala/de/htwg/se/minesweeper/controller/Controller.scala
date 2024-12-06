package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.{Field, Move, Symbols}
import de.htwg.se.minesweeper.util.Observable
import de.htwg.se.minesweeper.model.Game
import de.htwg.se.minesweeper.model.FieldFactory
import de.htwg.se.minesweeper.difficulty.DifficultyStrategy


case class Controller(var field: Field, game: Game) extends Observable:

    private var undoStack: List[Command] = Nil
    var isFirstMove: Boolean = true
    
    def firstMove(x: Int, y: Int, game: Game) = 
        field = game.premierMove(x, y, field, game)
        notifyObservers

    def uncoverField(x: Int , y: Int, game: Game) = 
        val cmd = new UncoverCommand(this, x, y)
        executeCommand(cmd) 


    def setDifficulty(strategy: DifficultyStrategy): Unit = {
        game.setDifficultyStrategy(strategy)
        field = FieldFactory.createField(game.side, Symbols.Covered)
        notifyObservers
    }

    def executeCommand(command: Command): Unit = {
        command.execute()
        undoStack = command :: undoStack
    }

    def undo(): Unit = {
        undoStack match {
        case Nil => println("Nothing to undo")
        case head :: tail =>
            head.undo()
            undoStack = tail
        }
    }
    
    override def toString = field.toString
