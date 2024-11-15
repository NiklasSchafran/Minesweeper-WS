package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.{Field, Game, Status}
import de.htwg.se.minesweeper.util.{Observable, Observer}
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar

class ControllerSpec extends AnyWordSpec with Matchers with MockitoSugar {
  "A Controller" when {
    "new" should {
      "initialize with a given field and game" in {
        val field = mock[Field]
        val game = mock[Game]
        val controller = Controller(field, game)

        controller.field shouldBe field
        controller.game shouldBe game
      }
    }

    "firstMove is called" should {
      "update the field based on the game's first move logic and notify observers" in {
        val initialField = mock[Field]
        val updatedField = mock[Field]
        val game = mock[Game]
        when(game.premierMove(1, 2, initialField, game)).thenReturn(updatedField)

        val controller = Controller(initialField, game)
        val observer = mock[Observer]
        controller.add(observer)
        
        controller.firstMove(1, 2, game)

        controller.field shouldBe updatedField
        verify(game).premierMove(1, 2, initialField, game)
        verify(observer).update
      }
    }

    "uncoverField is called" should {
      "update the field by uncovering the specified cell and notify observers" in {
        val initialField = mock[Field]
        val updatedField = mock[Field]
        val game = mock[Game]
        when(initialField.open(1, 2, game)).thenReturn(updatedField)

        val controller = Controller(initialField, game)
        val observer = mock[Observer]
        controller.add(observer)

        controller.uncoverField(1, 2, game)

        controller.field shouldBe updatedField
        verify(initialField).open(1, 2, game)
        verify(observer).update
      }
    }
  }
}