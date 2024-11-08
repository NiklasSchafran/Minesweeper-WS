package de.htwg.se.minesweeper.model

object Area:

    def inArea(x: Int, y: Int, side: Int): Boolean = {x >= 0 && x <= side && y >= 0 && y <= side}