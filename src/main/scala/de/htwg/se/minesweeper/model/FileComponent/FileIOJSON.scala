package de.htwg.se.minesweeper.model.FileComponent

import de.htwg.se.minesweeper.model.FieldComponent._
import de.htwg.se.minesweeper.model.GameComponent._
import io.circe._
import io.circe.syntax._
import io.circe.parser._
import java.io._

// FileIO für JSON
class FileIOJSON {
/*
  // --- Encoder / Decoder für Symbols ---
  implicit val symbolsEncoder: Encoder[Symbols] = Encoder.encodeString.contramap[Symbols](_.toString)
  implicit val symbolsDecoder: Decoder[Symbols] = Decoder.decodeString.emap {
    case "-" => Right(Covered)
    case "*" => Right(Bomb)
    case " " => Right(Empty)
    case "0" => Right(Zero)
    case "1" => Right(One)
    case "2" => Right(Two)
    case "3" => Right(Three)
    case "4" => Right(Four)
    case "5" => Right(Five)
    case "6" => Right(Six)
    case "7" => Right(Seven)
    case "8" => Right(Eight)
    case other => Left(s"Unknown symbol: $other")
  }

  // --- Encoder / Decoder für Matrix[Symbols] ---
  implicit val matrixEncoder: Encoder[Matrix[Symbols]] = Encoder.encodeVector[Vector[Symbols]].contramap(_.rows)
  implicit val matrixDecoder: Decoder[Matrix[Symbols]] = Decoder.decodeVector[Vector[Symbols]].map(new Matrix(_))

  // --- Encoder / Decoder für Field ---
  implicit val fieldEncoder: Encoder[Field] = deriveEncoder[Field]
  implicit val fieldDecoder: Decoder[Field] = deriveDecoder[Field]

  // --- Laden ---
  def load: FieldInterface = {
    val source = scala.io.Source.fromFile("field.json")
    val jsonString = try source.mkString finally source.close()
    val json = parse(jsonString).getOrElse(Json.Null)

    val emptyMatrix: Matrix[Symbols] = new Matrix(Vector.fill(3)(Vector.fill(3)(Empty): Vector[Symbols]))
    val field = Field(emptyMatrix, emptyMatrix)

    json.as[Field].getOrElse(field.newField(3, Covered))
  }

  // --- Speichern ---
  def save(field: FieldInterface): Unit = {
    val pw = new PrintWriter(new File("field.json"))
    pw.write(field.asInstanceOf[Field].asJson.noSpaces)
    pw.close()
  }*/
}
