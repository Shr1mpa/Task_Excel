package org.example

object FormulaParser {
    fun parse(formula: String, resolver: (Label) -> Cell?): List<Any?> {
        return formula.removePrefix("=").split('+').map { it.trim() }.map { labelStr ->
            val label = Label.fromString(labelStr) ?: return listOf("!err")
            val cell = resolver(label) ?: return listOf("!err")
            cell.evaluate()
        }
    }
}