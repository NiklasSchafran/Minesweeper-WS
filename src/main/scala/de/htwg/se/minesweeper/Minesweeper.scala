package de.htwg.se.minesweeper

import model._
import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.controller.ControllerInterface
import de.htwg.se.minesweeper.aview.*
import de.htwg.se.minesweeper.model.GameComponent.*
import de.htwg.se.minesweeper.model.FieldComponent.*
import scala.concurrent.ExecutionContext.Implicits.global
import com.google.inject.Guice
import scala.concurrent.Future

object Minesweeper {
  def main(args: Array[String]): Unit = {

    val injector = Guice.createInjector(new MinesweeperModule)
    val controller = injector.getInstance(classOf[ControllerInterface])

    val tui: TUIView = TUI(controller)
    val gui: GUIView = new GUI(controller)

    tui.run()
    gui.visible = true
  }
}