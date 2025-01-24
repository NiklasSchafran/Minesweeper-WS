package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class SymbolsSpec extends AnyWordSpec with Matchers {

  "The Symbols enum" should {

    "correctly represent Covered as '-'" in {
      Symbols.Covered.toString shouldBe "-"
    }

    "correctly represent Bomb as '*'" in {
      Symbols.Bomb.toString shouldBe "*"
    }

    "correctly represent Empty as ' '" in {
      Symbols.Empty.toString shouldBe " "
    }

    // Add tests for the rest of the symbols similarly
    "correctly represent Zero as '0'" in {
      Symbols.Zero.toString shouldBe "0"
    }

    "correctly represent One as '1'" in {
      Symbols.One.toString shouldBe "1"
    }

    "correctly represent Two as '2'" in {
      Symbols.Two.toString shouldBe "2"
    }

    // Continue for Three, Four, etc. until Eight
    "correctly represent Eight as '8'" in {
      Symbols.Eight.toString shouldBe "8"
    }
  }
}
