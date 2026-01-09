package de.htwg.se.minesweeper.controller

//import de.htwg.se.minesweeper.model.F.{Field, Move, Symbols}
import de.htwg.se.minesweeper.util.Observable
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.model.GameComponent._
import de.htwg.se.minesweeper.model.FieldComponent._
import de.htwg.se.minesweeper.difficulty.DifficultyStrategy
import de.htwg.se.minesweeper.model.FileComponent.FileIOJSON


case class Controller(var field: FieldInterface, game: Game) extends ControllerInterface {

    private var undoStack: List[Command] = Nil
    private var _bFirstMove: Boolean = true
    val fileIo = new FileIOJSON()

    def bFirstMove: Boolean = _bFirstMove
    def bFirstMove_=(value: Boolean): Unit = {
        _bFirstMove = value
    }

    def firstMove(x: Int, y: Int, game: Game): Unit = {
        field = game.premierMove(x, y, field, game)
        bFirstMove = false
        notifyObservers
    }

    def uncoverField(x: Int , y: Int, game: Game): Unit = {
        val cmd = new UncoverCommand(this, x, y)
        executeCommand(cmd)
    }


    def setDifficulty(strategy: DifficultyStrategy): Unit = {
        game.setDifficultyStrategy(strategy)
        field = FieldFactory.createField(game.side, Covered)
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

    def save(): Unit = {
        //fileIo.save(field)
    }

    def load(): Unit = {
        //field = fileIo.load
        notifyObservers
    }
    
    override def toString = field.toString
}