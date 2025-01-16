package de.htwg.se.minesweeper

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.minesweeper.controller.*
import de.htwg.se.minesweeper.model.GameComponent.*
import de.htwg.se.minesweeper.model.FieldComponent.*
import de.htwg.se.minesweeper.model.FileComponent.*

class MinesweeperModule extends AbstractModule {

  val defaultRows: Int = 10
  val defaultColumns: Int = 10
  val defaultMines: Int = 20

  override def configure(): Unit = {
   
    bind(classOf[GameInterface]).to(classOf[Game])
    bind(classOf[FieldInterface])
        .toInstance(new Field(
            Matrix(Vector.fill(defaultRows, defaultColumns)(Symbols.Covered)),
            Matrix(Vector.fill(defaultRows, defaultColumns)(Symbols.Covered))
    ))


    bind(classOf[ControllerInterface]).to(classOf[Controller])

    //bind[FileIOInterface].to[FileIOJason]
    //bind[FileIOInterface].to[FileIOXml]
  }
}

 