package org.example

class TableUtils {
    companion object {
        fun build(): Table {
            print("Введіть кількість рядків: ")
            val rows = readln().toInt()

            print("Введіть кількість колонок: ")
            val cols = readln().toInt()

            val table = Table(rows, cols)

            println("Введіть дані:")

            for (r in 0 until rows) {
                for (c in 0 until cols) {
                    val label = Label(r, c)
                    print("${label}: ")
                    val input = readln()
                    table.setCell(r, c, Cell.fromInput(input))
                }
            }

            return table
        }

        fun evaluate(table: Table) {
            table.forEachCell { row, col, cell ->
                cell.evaluate(table)
            }
        }

        fun print(table: Table, header: String = "") {
            if (header.isNotBlank()) println("\n$header")

            print("     ")
            for (c in 0 until table.cols) {
                val colLabel = Label(0, c).toString().takeWhile { it.isLetter() }
                print(colLabel.padEnd(10))
            }
            println()

            for (r in 0 until table.rows) {
                print("${r + 1}.  ")
                for (c in 0 until table.cols) {
                    val value = table.getCell(r, c).display()
                    print(value.padEnd(10))
                }
                println()
            }
        }
    }
}
