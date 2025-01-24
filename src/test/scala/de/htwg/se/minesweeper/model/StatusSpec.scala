package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class StatusSpec extends AnyWordSpec with Matchers {

  "The Status enum" should {
    "have a Playing status" in {
      Status.Playing shouldBe a [Status]
    }

    "have a Won status" in {
      Status.Won shouldBe a [Status]
    }

    "have a Lost status" in {
      Status.Lost shouldBe a [Status]
    }

    "allow comparison of different statuses" in {
      Status.Playing should not be Status.Won
      Status.Won should not be Status.Lost
      Status.Lost should not be Status.Playing
    }
  }
}
