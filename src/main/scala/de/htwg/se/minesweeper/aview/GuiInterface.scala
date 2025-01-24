package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.util.Observer

trait GUIView {
  def visible: Boolean
  def visible_=(v: Boolean): Unit
}

