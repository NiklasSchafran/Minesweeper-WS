package de.htwg.se.minesweeper.model
// Matrix[T] is a class for a 2 dimensional generic Matrix
// Primary constructor takse a Vector of Type Vector[T] witch represents the rows 
case class Matrix[T](rows: Vector[Vector[T]]):
    // here is the auxilary Constructor witch fills each element witch filling element ->generates a matrix instance 
    def this(size: Int, filling: T) = this(Vector.tabulate(size, size) {(row, col) => filling})
    // initalise Integer size with number of rows in the Matrix rows.size
    val size: Int = rows.size
    // returns value of a cell at given row & column
    def cell(row: Int, col: Int): T = rows(row)(col)
    // returns a Vector, representing entire row at given row Index
    def row(row: Int)= rows(row)
    // returns a new matrix with all cells filled with given filling element
    def fill(filling: T): Matrix[T] = copy(Vector.tabulate(size,size){(row, col) => filling})
    // returns a new matrix with one Cell changed with the filling element
    def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))