case class Cell(x:Int, y:Int)

val cell1 = Cell(4,5)
cell1.x
cell1.y

val cell2 = Cell(3,7)

case class Field(cells: Array[Cell])

val field1 = Field(Array.ofDim[Cell](1))
field1.cells(0)=cell1
field1.cells(0).x
field1.cells(0).y



// here we can test the Playfield for Minesweeper

val endl = sys.props("line.separator")
def bar(cellWidth: Int = 3, cellNum: Int = 3)= (("+" + "-" * cellWidth) * cellNum) + "+" + endl

bar(3,9)

//next one

def cells(cellWidth: Int = 3, cellNum: Int = 3) = ("|" + " " * cellWidth) * cellNum + "|" + endl
cells(9,9)

def mesh(cellWidth: Int = 3, cellNum: Int = 10) = (bar(cellWidth, cellNum) + cells(cellWidth, cellNum)) * cellNum + bar(cellWidth, cellNum)

println(mesh())

