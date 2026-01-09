package de.htwg.se.minesweeper.util

trait Observer {
  def update(): Unit
}

trait Observable {

  private var subscribers: Vector[Observer] = Vector.empty

  def add(s: Observer): Unit =
    subscribers = subscribers :+ s

  def remove(s: Observer): Unit =
    subscribers = subscribers.filterNot(_ == s)

  def notifyObservers(): Unit =
    subscribers.foreach(_.update())
}
