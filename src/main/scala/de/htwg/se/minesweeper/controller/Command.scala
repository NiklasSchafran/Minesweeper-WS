package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.Field

trait Command {
    def execute(): Unit
    def undo(): Unit
}

class UncoverCommand(controller: Controller, x: Int, y: Int) extends Command {
    private var previousState: Option[Field] = None

    override def execute(): Unit = {
        previousState = Some(controller.field.copy())

        val updateTupel = controller.field.open(x, y, controller.game)
        controller.field = updateTupel._1
        controller.game.gameState = updateTupel._2
        
        controller.notifyObservers
    }

    override def undo(): Unit = {
        previousState.foreach(state => controller.field = state)
        controller.notifyObservers
    }
}