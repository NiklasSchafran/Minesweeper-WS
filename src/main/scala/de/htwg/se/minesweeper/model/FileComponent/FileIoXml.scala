package de.htwg.se.minesweeper.model.FileComponent


import de.htwg.se.minesweeper.model.FieldComponent.*
import de.htwg.se.minesweeper.model.GameComponent.*
import java.io._
import scala.xml.{XML, PrettyPrinter, Elem, NodeSeq}

class FileIOXML extends FileIOInterface {

  override def load: FieldInterface = {
    val file = scala.xml.XML.loadFile("field.xml")
    val size = (file \\ "field" \ "@size").text.toInt
    val matrix = (file \\ "cell").map { cellNode =>
      val row = (cellNode \ "@row").text.toInt
      val col = (cellNode \ "@col").text.toInt
      val value = (cellNode \ "@value").text match {
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
      (row, col, value)
    }

    val emptyMatrix = Matrix(Vector.fill(size, size)(Symbols.Empty))
    val field = Field(emptyMatrix, emptyMatrix)
    
    // Die newField-Methode aufrufen, um das gewünschte Field zu erstellen

    val newField = field.newField(size, Symbols.Covered) // Field muss FieldInterface implementieren
    matrix.foldLeft(newField) { case (field, (row, col, value)) =>
      field.playerMatrix.replaceCell(row, col, value)
      field
    }
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
      {
      for {
        row <- 0 until field.size
        col <- 0 until field.size
      } yield <cell row={row.toString} col={col.toString} value={field.cell(row, col).toString}/>
      }
    </field>
  }
}