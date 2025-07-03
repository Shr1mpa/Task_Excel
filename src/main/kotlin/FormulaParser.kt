package org.example

object FormulaParser {
    fun parse(formula: String, table: Table): List<Any?> {
        return formula.removePrefix("=").split('+').map { it.trim() }.map { labelStr ->
            val label = Label.fromString(labelStr) ?: return listOf("!err")
            val cell = table.getCellByLabel(label) ?: return listOf("!err")
            cell.evaluate(table)
        }
    }
}