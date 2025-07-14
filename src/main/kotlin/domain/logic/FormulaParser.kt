package org.example.domain.logic

import org.example.domain.model.Cell
import org.example.domain.model.Label

object FormulaParser {
    fun parse(formula: String, resolver: (Label) -> Cell?): List<Any?> {
        return formula.removePrefix("=").split('+').map { it.trim() }.map { labelStr ->
            val label = Label.Companion.fromString(labelStr) ?: return listOf("!err")
            val cell = resolver(label) ?: return listOf("!err")
            cell.evaluate()
        }
    }
}