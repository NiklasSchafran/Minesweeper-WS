package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.FieldComponent._

trait Command {
    def execute(): Unit
    def undo(): Unit
}

  