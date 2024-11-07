package de.htwg.se.minesweeper.model

import java.io.{ByteArrayOutputStream, PrintStream}
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec 
{
    "A minesweeper Field" when 
    {
        "is empty" should 
        {
            
            val fieldOne = new Field(1)
            val field2 = new Field(2)
            val field3 = new Field(3)
            val endl = sys.props("line.separator")
            "have a bar as String of form '+---+---+---+'" in 
            {
                field3.bar() should be("+---+---+---+" + endl)
            }
            "also have a bar as String of form '+---+---+---+'" in 
            {
                field2.bar() should be("+---+---+---+" + endl)
            }
            "still have a bar as String of form '+---+---+---+'" in 
            {
                fieldOne.bar() should be("+---+---+---+" + endl)
            }
            "have a scalable bar" in 
            {
                fieldOne.bar(1,1) should be("+-+" + endl)
                field2.bar(2,1) should be("+--+" + endl)
                field2.bar(1,2) should be("+-+-+" + endl)
                field3.bar(3,3) should be("+---+---+---+" + endl)
            }
            "have cells as String of form '|   |   |   |'" in 
            {
                field3.cells(3,3) should be ("|   |   |   |" + endl)
                field3.cells() should be("|   |   |   |" + endl)
            }
            "have scalable cells" in 
            {
                fieldOne.cells(1,1) should be("| |" + endl)
                field2.cells(0,1) should be("||" + endl)
                field3.cells(3,3) should be("|   |   |   |" + endl)
                fieldOne.cells(0,3) should be("||||" + endl)
            }
            "have a mesh in the form" + 
            "+-+" + 
            "| |" + 
            "+-+" in 
            {
                fieldOne.mesh(1,1) should be("+-+" + endl + "| |" + endl + "+-+" + endl)
                fieldOne.mesh(1) should be("+-+" + endl + "| |" + endl + "+-+" + endl)
                field2.mesh(1) should be("+-+-+" + endl + "| | |" + endl + "+-+-+" + endl + "| | |" + endl + "+-+-+" + endl)
            }
            "filled with Empty" should 
            {
                val field = new Field(3)
                "be empty initially with Field(3)" in 
                {
                     field.toString() should be("+---+---+---+" + endl + "|   |   |   |" + endl + "+---+---+---+" + endl + "|   |   |   |" + endl + "+---+---+---+" + endl + "|   |   |   |" + endl + "+---+---+---+" +endl)
                }
            }


        }
        "is Equal" should {
            val fieldx = new Field(3)
            val fieldy = new Field(3)
            val fieldz = new Field(1)

            "be true with fieldx.equals(fieldy)" in {
                fieldx.equals(fieldy) should be(true)
                fieldz.equals(fieldy) should be(false)
                fieldy.equals(fieldy) should be(true)
            }
        }
    }
     "The Main object" when {
    "executed" should {
      "print the welcome message and create a field" in {
        val outputStreamCaptor = new ByteArrayOutputStream()
        Console.withOut(outputStreamCaptor) {
          de.htwg.se.minesweeper.Main.main(Array.empty)
        }

        val capturedOutput = outputStreamCaptor.toString.trim
        capturedOutput should include ("Welcome to Minesweeper")
        // Note: Additional assertions might be added based on your requirements.
            }
        }
    }
}