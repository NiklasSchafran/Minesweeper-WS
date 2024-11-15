package de.htwg.se.minesweeper.model

// Field extended with 2 parameters (matrix: Matrix[Symbols], hidden: Matrix[Symbols])
case class Field(matrix: Matrix[Symbols], bomben: Matrix[Symbols]):
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


    def isBomb(x: Int, y: Int, m: Matrix[Symbols]): Boolean = {
        val si = m.size-1
        printf("isbomb si = ")
        println(si)
        if(inArea(x, y,si)){
            if(m.cell(y, x) == Symbols.Bomb){
                return true
            }
        }
        return false
    }

    def inArea(x: Int, y: Int, side: Int): Boolean = {x >= 0 && x <= side && y >= 0 && y <= side}

 

    // only open Field function without checking if game is won or not
    def Num(x : Int, y : Int, bMatrix: Matrix[Symbols], pMatrix: Matrix[Symbols]): Matrix[Symbols] = {

        var tmpMatrix = pMatrix
        val si = bMatrix.size - 1

        if(!(inArea(x, y, si)) || pMatrix.cell(y, x) != Symbols.Covered){
            return pMatrix
        }        
        var minesFound = 0
        if(isBomb(x+1, y+1, bMatrix)){minesFound += 1}
        if(isBomb(x, y+1, bMatrix)){minesFound+= 1}
        if(isBomb(x-1, y+1, bMatrix)){minesFound+= 1}
        if(isBomb(x+1, y, bMatrix)){minesFound+= 1}
        if(isBomb(x-1, y, bMatrix)){minesFound+= 1}
        if(isBomb(x+1, y-1, bMatrix)){minesFound+= 1}
        if(isBomb(x, y-1, bMatrix)){minesFound+= 1}
        if(isBomb(x-1, y-1, bMatrix)){minesFound+= 1}

        if(minesFound == 0){
            tmpMatrix = tmpMatrix.replaceCell(y, x, Symbols.Empty)

            if(inArea(x+1, y+1, si))
                tmpMatrix = Num(x+1, y+1, bMatrix, tmpMatrix)
            if(inArea(x, y+1, si))
                tmpMatrix = Num(x, y+1, bMatrix, tmpMatrix)
            if(inArea(x-1, y+1, si))
                tmpMatrix = Num(x-1, y+1, bMatrix, tmpMatrix)
            if(inArea(x+1, y, si))
                tmpMatrix = Num(x+1, y, bMatrix, tmpMatrix)
            if(inArea(x-1, y, si))
                tmpMatrix = Num(x-1, y, bMatrix, tmpMatrix)
            if(inArea(x+1, y-1, si))
                tmpMatrix = Num(x+1, y-1, bMatrix, tmpMatrix)
            if(inArea(x, y-1, si))
                tmpMatrix = Num(x, y-1, bMatrix, tmpMatrix)
            if(inArea(x-1, y-1, si))
                tmpMatrix = Num(x-1, y-1, bMatrix, tmpMatrix)
            
            return tmpMatrix   
        }else{
            val symb = minesFound match {
                            case 0 => Symbols.Zero
                            case 1 => Symbols.One
                            case 2 => Symbols.Two
                            case 3 => Symbols.Three
                            case 4 => Symbols.Four
                            case 5 => Symbols.Five
                            case 6 => Symbols.Six
                            case 7 => Symbols.Seven
                            case 8 => Symbols.Eight
                        }
            tmpMatrix = tmpMatrix.replaceCell(y, x, symb)
        }
        return tmpMatrix
    }



    // open call a function (openFl) who has another function (openF) as parameter
    def open(x: Int, y: Int, spiel: Game): Field = 
        if(bombenMatrix.cell(y, x) == Symbols.Bomb){spiel.gameState = Status.Lost}else{
        playerMatrix = Num(x, y, bombenMatrix, playerMatrix)}
        val nextField = new Field(playerMatrix, bombenMatrix)
        nextField
        


    // used for printing field in main
    override def toString(): String = mesh()