package org.example

class Table(val rows: Int, val cols: Int) {
    private val grid: Array<Array<Cell>> = Array(rows) { Array(cols) { Cell.ErrorCell } }

    fun setCell(row: Int, col: Int, cell: Cell) {
        grid[row][col] = cell
    }

    fun getCell(row: Int, col: Int): Cell = grid[row][col]

    fun getCellByLabel(label: String): Cell? {
        val col = label[0].uppercaseChar() - 'A'
        val row = label.substring(1).toIntOrNull()?.minus(1) ?: return null
        return if (row in 0 until rows && col in 0 until cols) grid[row][col] else null
    }

    fun forEachCell(action: (row: Int, col: Int, cell: Cell) -> Unit) {
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                action(r, c, grid[r][c])
            }
        }
    }
}
