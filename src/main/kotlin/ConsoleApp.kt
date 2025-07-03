package org.example

class ConsoleApp {
    companion object {
    fun run() {
        val table = TableUtils.buildInteractive()
        TableUtils.print(table, "Введена таблиця:")
        TableUtils.evaluate(table)
        TableUtils.print(table, "Обчислена таблиця:")
        }
    }
}