package de.htwg.se.minesweeper

import model._
import de.htwg.se.minesweeper.controller.{Controller, ControllerInterface}
import de.htwg.se.minesweeper.aview.{GUI, GUIView, TUI, TUIView}
import de.htwg.se.minesweeper.model.GameComponent._
import de.htwg.se.minesweeper.model.FieldComponent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Minesweeper {

  def main(args: Array[String]): Unit = {

    val msGame = new Game()

    val coveredMatrix: Matrix[Symbols] = new Matrix(Vector.fill(10,10)(Covered): Vector[Vector[Symbols]])

    val coveredField =
      Field(coveredMatrix, coveredMatrix)

    val controller: ControllerInterface =
      new Controller(coveredField, msGame)

    val tui: TUIView = new TUI(controller)
    val gui: GUIView = new GUI(controller)

    tui.run()
    gui.visible = true
  }
}
