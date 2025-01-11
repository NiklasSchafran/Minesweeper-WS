package de.htwg.se.minesweeper.model.FieldComponent
import de.htwg.se.minesweeper.model._
import de.htwg.se.minesweeper.model.FieldComponent.MatrixInterface
// Matrix[T] is a class for a 2 dimensional generic Matrix
// Primary constructor takse a Vector of Type Vector[T] witch represents the rows 

case class Matrix[T](rows: Vector[Vector[T]]) extends MatrixInterface[T] {
    val size: Int = rows.size

    def cell(row: Int, col: Int): T = rows(row)(col)
    def row(row: Int): Vector[T] = rows(row)

    def fill(filling: T): Matrix[T] = copy(Vector.tabulate(size, size) { (row, col) => filling })

    def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = 
        copy(rows.updated(row, rows(row).updated(col, cell)))
}
