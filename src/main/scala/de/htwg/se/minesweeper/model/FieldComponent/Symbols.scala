package de.htwg.se.minesweeper.model.FieldComponent

// Basis-Trait für alle Symbole
sealed trait Symbols {
  override def toString: String
}

// Verschiedene Symbole
case object Covered extends Symbols {
  override def toString: String = "-"
}

case object Empty extends Symbols {
  override def toString: String = " "
}

case object Bomb extends Symbols {
  override def toString: String = "*"
}

// Zahlen von 0 bis 8
case object Zero extends Symbols {
  override def toString: String = "0"
}

case object One extends Symbols {
  override def toString: String = "1"
}

case object Two extends Symbols {
  override def toString: String = "2"
}

case object Three extends Symbols {
  override def toString: String = "3"
}

case object Four extends Symbols {
  override def toString: String = "4"
}

case object Five extends Symbols {
  override def toString: String = "5"
}

case object Six extends Symbols {
  override def toString: String = "6"
}

case object Seven extends Symbols {
  override def toString: String = "7"
}

case object Eight extends Symbols {
  override def toString: String = "8"
}
