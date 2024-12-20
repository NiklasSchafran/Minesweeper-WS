package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.{Field, Symbols, Game}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.Status

class CommandSpec extends AnyWordSpec with Matchers {

  "An UncoverCommand" should {
    val game = new Game(Status.Playing)
    game.side = 3
    val field = new Field(3, Symbols.Covered)
    val controller = new Controller(field, game)

    "execute and uncover the field" in {
        val initialField = controller.field.copy()
        val command = new UncoverCommand(controller, 1, 1)
        command.execute()
        controller.field.playerMatrix.cell(1, 1) should not be Symbols.Covered
        command.undo()
        controller.field.playerMatrix.cell(1, 1) should be (Symbols.Covered)
      }
  }
}