package de.htwg.se.minesweeper.model

//test
case class Field(cellNum: Int){
    val endl = sys.props("line.separator")
    // defines the bar
    def bar(cellWidth: Int = 3, cellNum: Int = 3) = (("+" + "-" * cellWidth) * cellNum) + "+" + endl
    // defines the cells
    def cells(cellWidth: Int = 3, cellNum: Int = 3) = ("|" + " " * cellWidth) * cellNum + "|" + endl
    // defines the grid and default size is 10x10 field
    def mesh(cellWidth: Int = 3, cellNum: Int = cellNum) = (bar(cellWidth, cellNum) + cells(cellWidth, cellNum)) * cellNum + bar(cellWidth, cellNum)
    // prints grid default size is 10x10
    println(mesh())}