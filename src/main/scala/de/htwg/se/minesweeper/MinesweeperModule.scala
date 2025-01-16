package de.htwg.se.minesweeper

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.minesweeper.controller.*
import de.htwg.se.minesweeper.model.GameComponent.*
import de.htwg.se.minesweeper.model.FieldComponent.*

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
  }
}

 // Bind constants for default settings
    /*bindConstant().annotatedWith(Names.named("DefaultRows")).to(defaultRows)
    bindConstant().annotatedWith(Names.named("DefaultColumns")).to(defaultColumns)
    bindConstant().annotatedWith(Names.named("DefaultMines")).to(defaultMines)

    // Bind interfaces to their implementations
    bind[FieldInterface].to[Field]
    bind[MatrixInterface[Symbols]].to[Matrix[Symbols]]
    bind[ControllerInterface].to[Controller]

    // Bind specific instances of Field for different presets
    bind[FieldInterface].annotatedWithName("tiny").toInstance(new Field(
      Matrix(Vector.fill(5, 5)(Symbols.Empty)),
      Matrix(Vector.fill(5, 5)(Symbols.Empty))
    ))
    bind[FieldInterface].annotatedWithName("small").toInstance(new Field(
      Matrix(Vector.fill(8, 8)(Symbols.Empty)),
      Matrix(Vector.fill(8, 8)(Symbols.Empty))
    ))
    bind[FieldInterface].annotatedWithName("normal").toInstance(new Field(
      Matrix(Vector.fill(10, 10)(Symbols.Empty)),
      Matrix(Vector.fill(10, 10)(Symbols.Empty))
    ))
    bind[FieldInterface].annotatedWithName("large").toInstance(new Field(
      Matrix(Vector.fill(16, 16)(Symbols.Empty)),
      Matrix(Vector.fill(16, 16)(Symbols.Empty))
    ))*/