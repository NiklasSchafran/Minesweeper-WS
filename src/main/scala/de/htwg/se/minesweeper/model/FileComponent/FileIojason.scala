package de.htwg.se.minesweeper.model.FileComponent

import de.htwg.se.minesweeper.model.FieldComponent._
import de.htwg.se.minesweeper.model.GameComponent._
import io.circe._
import io.circe.generic.semiauto._
import io.circe.syntax._
import io.circe.parser._
import java.io._
import scala.io.Source

class FileIOJSON extends FileIOInterface {

  implicit val symbolsEncoder: Encoder[Symbols] = Encoder.encodeString.contramap[Symbols](_.toString)
  implicit val symbolsDecoder: Decoder[Symbols] = Decoder.decodeString.emap {
    case "-" => Right(Symbols.Covered)
    case "*" => Right(Symbols.Bomb)
    case " " => Right(Symbols.Empty)
    case "0" => Right(Symbols.Zero)
    case "1" => Right(Symbols.One)
    case "2" => Right(Symbols.Two)
    case "3" => Right(Symbols.Three)
    case "4" => Right(Symbols.Four)
    case "5" => Right(Symbols.Five)
    case "6" => Right(Symbols.Six)
    case "7" => Right(Symbols.Seven)
    case "8" => Right(Symbols.Eight)
    case other => Left(s"Unknown symbol: $other")
  }

  implicit val fieldEncoder: Encoder[Field] = deriveEncoder[Field]
  implicit val fieldDecoder: Decoder[Field] = deriveDecoder[Field]

  override def load: FieldInterface = {
    val source = scala.io.Source.fromFile("field.json")
    val jsonString = try source.mkString finally source.close()
    val json = parse(jsonString).getOrElse(Json.Null)
    val emptyMatrix = Matrix(Vector.fill(3, 3)(Symbols.Empty))
    val field = Field(emptyMatrix, emptyMatrix)

    json.as[Field].getOrElse(field.newField(3, Symbols.Covered)) 
  }

  override def save(field: FieldInterface): Unit = {
    val pw = new PrintWriter(new File("field.json"))
    pw.write(field.asInstanceOf[Field].asJson.noSpaces)
    pw.close()
  }
}
