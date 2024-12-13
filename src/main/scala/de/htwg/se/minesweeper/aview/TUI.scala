package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.util.Observer
import scala.io.StdIn.readLine
import de.htwg.se.minesweeper.model.Move
import de.htwg.se.minesweeper.model.Status
import scala.util.{Try, Success, Failure}
import de.htwg.se.minesweeper.difficulty.{DifficultyStrategy, EasyDifficulty, MediumDifficulty, HardDifficulty}

class TUI(controller: Controller) extends Observer:
    
    controller.add(this)

    var game = controller.game
    var state = controller.game.state

    def run =
        //println(controller.field.toString)
        selectDifficulty()
        firstMoveInputParser
        parseInputandPrintLoop()
        
    override def update = println(controller.field.toString())

    def userIn2(input: String): Option[Move] = 
      val moveCheck = input match
        case "q" => None
        case _ => {
          val charAccumulator = input.toCharArray()
          
          val action = charAccumulator(0) match
            case 'o' => "open"
            case 'z' => "undo"
            case _ => "open"
          val xAxis = charAccumulator(1).toString.toInt
          val yAxis = charAccumulator(2).toString.toInt
          Some(Move(action, xAxis, yAxis))
        }
      moveCheck


    def parseInputandPrintLoop(): Unit = {
      println("Enter your move:")
      
      val result = Try {
        userIn2(readLine) match {
          case None =>
            println("Goodbye!")
            System.exit(0)
          case Some(move) =>
            move.value match {
              case "open" => controller.uncoverField(move.x, move.y, game)
              case "undo" => controller.undo()
              case _      => println("Ungültige Eingabe")
            }
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
    
    def firstMoveInputParser: Unit =
      println("Enter your move:")
      userIn2(readLine) match
        case None => System.exit(0)
        case Some(move) => 
          move.value match {
            case "open" => controller.firstMove(move.x, move.y, game)
          }


    def selectDifficulty(): Unit = {
        println("Enter the Difficulty Level")
        println("0 fuer 3x3 und 1er bombe)")
        println("1 fuer 8x8 und 6 bomben")
        println("2 fuer 16x16 und 40 bomben")

        val level = scala.io.StdIn.readInt()

        val selectedStrategy: DifficultyStrategy = level match {
            case 0 => new EasyDifficulty
            case 1 => new MediumDifficulty
            case 2 => new HardDifficulty
            case _ =>  new EasyDifficulty
        }
        controller.setDifficulty(selectedStrategy)

    }
          