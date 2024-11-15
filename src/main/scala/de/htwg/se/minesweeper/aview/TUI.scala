package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.util.Observer
import scala.io.StdIn.readLine
import de.htwg.se.minesweeper.model.Move

class TUI(controller: Controller) extends Observer:
    
    controller.add(this)

    var game = controller.game
    var state = controller.game.state

    def run =
        println(controller.field.toString)
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
            case _ => "open"
          val xAxis = charAccumulator(1).toString.toInt
          val yAxis = charAccumulator(2).toString.toInt
          Some(Move(action, xAxis, yAxis))
        }
      moveCheck


    def parseInputandPrintLoop(): Unit = {
      println("Enter your move:")
      userIn2(readLine) match
        case None => System.exit(0)
        case Some(move) => 
          move.value match {
            case "open" => controller.uncoverField(move.x, move.y, game)
          }
      game.checkGameState(game)
      if(game.gameState == Status.Lost) System.exit(0)
      if(game.gameState == Status.Won) System.exit(0)
      parseInputandPrintLoop()
    }

    def firstMoveInputParser: Unit =
      //lets go
      println("Enter your move:")
      userIn2(readLine) match
        case None => System.exit(0)
        case Some(move) => 
          move.value match {
            case "open" => controller.firstMove(move.x, move.y, game) // here is the change
          }
      game.checkGameState(game)
      if(game.gameState == Status.Lost) System.exit(0)
      if(game.gameState == Status.Won) System.exit(0)


enum Status:
    case Playing, Won, Lost

      