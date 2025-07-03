package org.example

class ConsoleApp {
    companion object {
    fun run() {
        val table = TableUtils.build()
        TableUtils.print(table, "Введена таблиця:")
        TableUtils.evaluate(table)
        TableUtils.print(table, "Обчислена таблиця:")
        }
    }
}