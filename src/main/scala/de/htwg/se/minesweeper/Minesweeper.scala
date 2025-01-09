package de.htwg.se.minesweeper

import model.{Field, Matrix, Symbols, Game}
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.aview.TUI
import de.htwg.se.minesweeper.aview.GUI
import de.htwg.se.minesweeper.model.Status
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Minesweeper {
  def main(args: Array[String]): Unit = {
    var msGame = new Game(Status.Playing)
    var coveredField = new Field(10, Symbols.Covered)

    val controller = Controller(coveredField, msGame)

    val tui = new TUI(controller)
    val gui = new GUI(controller, tui)

    tui.run
    gui.visible = true
  }
}