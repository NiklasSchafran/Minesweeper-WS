package de.htwg.se.minesweeper.model.FileComponent


import de.htwg.se.minesweeper.model.FieldComponent.*
import de.htwg.se.minesweeper.model.GameComponent.*
import java.io._
import scala.xml.{XML, PrettyPrinter, Elem, NodeSeq}

class FileIoXml extends FileIOInterface {

  override def load: FieldInterface = {
    val file = scala.xml.XML.loadFile("field.xml")
    val size = (file \\ "field" \ "@size").text.toInt

    // PlayerMatrix extrahieren
    val playerMatrixData = (file \\ "playerMatrix" \\ "cell").map { cellNode =>
      val row = (cellNode \ "@row").text.toInt
      val col = (cellNode \ "@col").text.toInt
      val value = parseSymbol((cellNode \ "@value").text)
      (row, col, value)
    }

    // BombMatrix extrahieren
    val bombMatrixData = (file \\ "bombMatrix" \\ "cell").map { cellNode =>
      val row = (cellNode \ "@row").text.toInt
      val col = (cellNode \ "@col").text.toInt
      val value = parseSymbol((cellNode \ "@value").text)
      (row, col, value)
    }

    val emptyPlayerMatrix = Matrix(Vector.fill(size, size)(Symbols.Empty))
    val emptyBombMatrix = Matrix(Vector.fill(size, size)(Symbols.Empty))
    val field = Field(emptyPlayerMatrix, emptyBombMatrix)

    val updatedPlayerMatrix = playerMatrixData.foldLeft(field.playerMatrix) { case (matrix, (row, col, value)) =>
      matrix.replaceCell(row, col, value)
    }

    val updatedBombMatrix = bombMatrixData.foldLeft(field.bombenMatrix) { case (matrix, (row, col, value)) =>
      matrix.replaceCell(row, col, value)
    }

    Field(updatedPlayerMatrix, updatedBombMatrix)
  }

  override def save(field: FieldInterface): Unit = {
    val pw = new PrintWriter(new File("field.xml"))
    val pp = new PrettyPrinter(120, 4)
    val xml = fieldToXml(field)
    pw.write(pp.format(xml))
    pw.close()
  }

  def fieldToXml(field: FieldInterface): Elem = {
    <field size={field.size.toString}>
      <playerMatrix>
        {
          for {
            row <- 0 until field.size
            col <- 0 until field.size
          } yield <cell row={row.toString} col={col.toString} value={field.cell(row, col).toString}/>
        }
      </playerMatrix>
      <bombMatrix>
        {
          for {
            row <- 0 until field.size
            col <- 0 until field.size
          } yield <cell row={row.toString} col={col.toString} value={field.cell(row, col).toString}/> 
        }
      </bombMatrix>
    </field>
  }

  private def parseSymbol(value: String): Symbols = value match {
    case "-" => Symbols.Covered
    case "*" => Symbols.Bomb
    case " " => Symbols.Empty
    case "0" => Symbols.Zero
    case "1" => Symbols.One
    case "2" => Symbols.Two
    case "3" => Symbols.Three
    case "4" => Symbols.Four
    case "5" => Symbols.Five
    case "6" => Symbols.Six
    case "7" => Symbols.Seven
    case "8" => Symbols.Eight
  }
}
//field.bombenmatirx.cell