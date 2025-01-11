package de.htwg.se.minesweeper

import model._
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.controller.ControllerInterface
import de.htwg.se.minesweeper.aview.TUI
import de.htwg.se.minesweeper.aview.GUI
import de.htwg.se.minesweeper.aview.GUIView
import de.htwg.se.minesweeper.aview.TUIView
import de.htwg.se.minesweeper.model.GameComponent.*
import de.htwg.se.minesweeper.model.FieldComponent.*
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Minesweeper {
  def main(args: Array[String]): Unit = {
    var msGame = new Game(Status.Playing)
    val CoveredMatrix = Matrix(Vector.fill(10, 10)(Symbols.Covered))
    val coveredField = Field(CoveredMatrix, CoveredMatrix)
    
    // Die newField-Methode aufrufen, um das gewünschte Field zu erstellen

    val controller: ControllerInterface = Controller(coveredField, msGame)

    //val controller = Controller(coveredField, msGame)


    val tui: TUIView = TUI(controller)
    val gui: GUIView = new GUI(controller)

    //val tui = new TUI(controller)
    //val gui = new GUI(controller)

    tui.run()
    gui.visible = true
  }
}