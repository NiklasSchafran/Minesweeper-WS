package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.util.Observer

trait TUIView extends Observer {
    def run(): Unit
}
