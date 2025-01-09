package de.htwg.se.minesweeper.model

// Field extended with 2 parameters (matrix: Matrix[Symbols], hidden: Matrix[Symbols])
case class Field(matrix: Matrix[Symbols], bomben: Matrix[Symbols]) extends FieldInterface:
    // second constructor 
    def this(size: Int, filling: Symbols)= this(new Matrix(size, filling), new Matrix(size, Symbols.Empty))
    val size = matrix.size
    // third constructor
    def this(matrix: Matrix[Symbols])= this(matrix, matrix)
    var playerMatrix = matrix
    var bombenMatrix = bomben 

    val endl = sys.props("line.separator")
    // defines the bar
    def bar(cellWidth: Int = 3, cellNum: Int = 3) = (("+" + "-" * cellWidth) * cellNum) + "+" + endl
    // defines the cells ("|" + " " * cellWidth) * cellNum + "|" + endl
    def cells(row: Int = 3, cellWidth: Int = 3) = matrix.row(row).map(_.toString).map(" " * ((cellWidth-1)/2) + _ + " " *((cellWidth -1)/2)).mkString("|","|","|") + endl
    // defines the grid and default size is 10x10 field def mesh(cellWidth: Int = 3, cellNum: Int = size) = (bar(cellWidth, cellNum) + cells(cellWidth, cellNum)) * cellNum + bar(cellWidth, cellNum)
    def mesh(cellWidth: Int = 3) = 
        (0 until size).map(cells(_, cellWidth)).mkString(bar(cellWidth, size), bar(cellWidth, size), bar(cellWidth, size))

    def open(x: Int, y: Int, spiel: Game): (Field, Status) = 
        if(bombenMatrix.cell(y, x) == Symbols.Bomb){
            spiel.gameState = Status.Lost
            playerMatrix = playerMatrix.replaceCell(y, x, Symbols.Bomb)
            val nextField = new Field(playerMatrix, bombenMatrix)
            (nextField, Status.Lost)
        }
        playerMatrix = spiel.Num(x, y, bombenMatrix, playerMatrix)
        val nextField = new Field(playerMatrix, bombenMatrix)
        (nextField, spiel.gameState)
        

    def cell(row: Int, col: Int): Symbols = matrix.cell(row, col)
    
    // used for printing field in main
    override def toString(): String = mesh()