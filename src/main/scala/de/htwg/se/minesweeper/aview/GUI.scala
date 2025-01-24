package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.controller.ControllerInterface
import de.htwg.se.minesweeper.util.Observer
import de.htwg.se.minesweeper.aview.GUIView
import de.htwg.se.minesweeper.model.GameComponent.*
import de.htwg.se.minesweeper.model.FieldComponent.*
import scala.swing._
import scala.swing.event._
import java.awt.event.{MouseEvent => AwtMouseEvent}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class GUI(controller: ControllerInterface) extends Frame with GUIView with Observer{
  controller.add(this)
  title = "Minesweeper"
  preferredSize = new Dimension(800, 600)
  var game = controller.game
  var firstM: Boolean = false

  private var _visible: Boolean = false

  override def visible: Boolean = _visible
  override def visible_=(v: Boolean): Unit = _visible = v

  var gridPanel: GridPanel = new GridPanel(controller.field.size, controller.field.size) {
    preferredSize = new Dimension(600, 600)
  }

  val difficultyComboBox: ComboBox[String] = new ComboBox(Seq("Easy", "Medium", "Hard"))
  val undoButton: Button = new Button("Undo")
  val exitButton: Button = new Button("Exit")

  val topPanel: FlowPanel = new FlowPanel {
    contents += undoButton
    contents += difficultyComboBox
    contents += exitButton
  }

  val mainPanel: BorderPanel = new BorderPanel {
    layout(topPanel) = BorderPanel.Position.North
    layout(gridPanel) = BorderPanel.Position.Center
  }

  contents = mainPanel

  listenTo(difficultyComboBox.selection, exitButton)
  reactions += {
    case SelectionChanged(`difficultyComboBox`) =>
      updateDifficulty()
    case ButtonClicked(`undoButton`) =>
      controller.undo()
    case ButtonClicked(`exitButton`) =>
      sys.exit(0)
  }

  def updateDifficulty(): Unit = {
    val difficulty = difficultyComboBox.selection.item
    difficulty match {
      case "Easy" => controller.setDifficulty(new de.htwg.se.minesweeper.difficulty.EasyDifficulty)
      case "Medium" => controller.setDifficulty(new de.htwg.se.minesweeper.difficulty.MediumDifficulty)
      case "Hard" => controller.setDifficulty(new de.htwg.se.minesweeper.difficulty.HardDifficulty)
    }
  }

  def refreshGrid(): Unit = {
    if (controller.field.size > 0) {
      gridPanel = new GridPanel(controller.field.size, controller.field.size) {
        preferredSize = new Dimension(600, 600)
      }

      for {
        row <- 0 until controller.field.size
        col <- 0 until controller.field.size
      } {
        val button = new Button {
          reactions += {
            case ButtonClicked(_) =>
                controller.uncoverField(col, row, game)
          }
          listenTo(mouse.clicks)
        }

        controller.field.cell(row, col) match {
          case Symbols.Covered => button.background = java.awt.Color.GREEN
          case Symbols.Empty => button.background = java.awt.Color.GRAY
          case Symbols.Bomb => button.background = java.awt.Color.RED
          case _ =>
            button.background = java.awt.Color.LIGHT_GRAY
            button.text = controller.field.cell(row, col).toString
        }

        gridPanel.contents += button
      }

      mainPanel.layout(gridPanel) = BorderPanel.Position.Center
      mainPanel.revalidate()
      mainPanel.repaint()
    }
  }

  override def update: Unit = {
    game = controller.game
    refreshGrid()
  }

  open()
  refreshGrid()
}
