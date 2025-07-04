package org.example

class Table(val rows: Int, val cols: Int) : EvaluationContext {
    private val grid: Array<Array<Cell>> = Array(rows) { Array(cols) { Cell.ErrorCell } }

    fun setCell(row: Int, col: Int, cell: Cell) {
        grid[row][col] = cell
    }

    fun getCell(row: Int, col: Int): Cell = grid[row][col]

    fun getCellByLabel(label: Label): Cell? {
        return if (label.row in 0 until rows && label.col in 0 until cols) {
            grid[label.row][label.col]
        } else null
    }


    fun forEachCell(action: (row: Int, col: Int, cell: Cell) -> Unit) {
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                action(r, c, grid[r][c])
            }
        }
    }

    override fun getCell(label: Label): Cell? = getCellByLabel(label)
}
