package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.controller.ControllerInterface
import de.htwg.se.minesweeper.util.Observer
import scala.io.StdIn.readLine
import de.htwg.se.minesweeper.model.GameComponent.*
import de.htwg.se.minesweeper.model.FieldComponent.*
import scala.util.{Try, Success, Failure}
import de.htwg.se.minesweeper.difficulty.{DifficultyStrategy, EasyDifficulty, MediumDifficulty, HardDifficulty}

class TUI(controller: ControllerInterface) extends Observer with TUIView:
    
    controller.add(this)

    var game = controller.game
    var state = controller.game.state
    @volatile private var difficultySelected = false


    def run() =
        selectDifficulty()
        parseInputandPrintLoop()
        
    override def update = println(controller.field.toString())

    def userIn2(input: String): Option[Move] = {
      if (input.matches("o\\d{2}")) {
        val xAxis = input.charAt(1).toString.toInt
        val yAxis = input.charAt(2).toString.toInt
        Some(Move("open", xAxis, yAxis))
      } else {
        None
      }
    }


    def parseInputandPrintLoop(): Unit = {
      println("Enter your move:")
      val input = readLine()

      val result = Try {
        userIn2(input) match {
          case None =>
            input match {
              case "0" => controller.setDifficulty(new de.htwg.se.minesweeper.difficulty.EasyDifficulty)
              case "1" => controller.setDifficulty(new de.htwg.se.minesweeper.difficulty.MediumDifficulty)
              case "2" => controller.setDifficulty(new de.htwg.se.minesweeper.difficulty.HardDifficulty)
              case "z" =>  controller.undo()
              case "q" => println("Goodbye2!"); System.exit(0);
              case _ => println("Ungültige Eingabe2"); parseInputandPrintLoop()
            }
          case Some(move) => controller.uncoverField(move.x, move.y, game)
        }
        game = controller.game
        if (game.gameState == Status.Lost) {
          println("You just lost!!!")
          System.exit(0)
        }
      }
      
      result match {
        case Success(_) => parseInputandPrintLoop()
        case Failure(e) =>
          println(s"Ein Fehler ist aufgetreten: ${e.getMessage}")
          parseInputandPrintLoop()
      }
    }
    


    def selectDifficulty(): Unit = {
      println("Enter the Difficulty Level")
      println("0 fuer 3x3 und 1er bombe")
      println("1 fuer 8x8 und 6 bomben")
      println("2 fuer 16x16 und 40 bomben")
    }
          