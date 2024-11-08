package de.htwg.se.minesweeper.model

case class Field(matrix: Matrix[Symbols]):
    // second constructor 
    def this(size: Int, filling: Symbols)= this(new Matrix(size, filling))
    val size = matrix.size

    val endl = sys.props("line.separator")
    // defines the bar
    def bar(cellWidth: Int = 3, cellNum: Int = 3) = (("+" + "-" * cellWidth) * cellNum) + "+" + endl
    // defines the cells ("|" + " " * cellWidth) * cellNum + "|" + endl
    def cells(row: Int = 3, cellWidth: Int = 3) = matrix.row(row).map(_.toString).map(" " * ((cellWidth-1)/2) + _ + " " *((cellWidth -1)/2)).mkString("|","|","|") + endl
    // defines the grid and default size is 10x10 field def mesh(cellWidth: Int = 3, cellNum: Int = size) = (bar(cellWidth, cellNum) + cells(cellWidth, cellNum)) * cellNum + bar(cellWidth, cellNum)
    def mesh(cellWidth: Int = 3) = 
        (0 until size).map(cells(_, cellWidth)).mkString(bar(cellWidth, size), bar(cellWidth, size), bar(cellWidth, size))
    // used for printing field in main
    override def toString(): String = mesh()