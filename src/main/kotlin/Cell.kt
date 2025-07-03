package org.example

sealed class Cell {
    abstract fun evaluate(table: Table): Any?
    abstract fun display(): String

    class ValueCell<T>(private val value: T) : Cell() {
        override fun evaluate(table: Table) = value
        override fun display(): String = when (value) {
            is String -> "\"$value\""
            else -> value.toString()
        }
    }

    class FormulaCell(private val formula: String) : Cell() {
        private var cached: Any? = null

        override fun evaluate(table: Table): Any? {
            if (cached != null) return cached

            val parts = formula.removePrefix("=").split('+').map { it.trim() }

            val values = parts.map { labelStr ->
                val label = Label.fromString(labelStr) ?: return@map "!err"
                val cell = table.getCellByLabel(label) ?: return@map "!err"
                cell.evaluate(table)
            }

            if (values.any { it == "!err" }) {
                cached = "!err"
                return cached
            }
            cached = when {
                values.all { it is Double } -> values.sumOf { it as Double }.toString()
                values.all { it is String } -> values.joinToString(separator = "") { it as String }
                else -> "!err"
            }

            return cached
        }

        override fun display(): String = cached?.toString() ?: formula
    }

    object ErrorCell : Cell() {
        override fun evaluate(table: Table) = "!err"
        override fun display() = "!err"
    }

    companion object {
        fun fromInput(input: String): Cell {
            return when {
                input.startsWith("=") -> FormulaCell(input)
                input.startsWith("\"") && input.endsWith("\"") -> ValueCell(input.removeSurrounding("\""))
                input.toDoubleOrNull() != null -> ValueCell(input.toDouble())
                else -> ErrorCell
            }
        }
    }
}