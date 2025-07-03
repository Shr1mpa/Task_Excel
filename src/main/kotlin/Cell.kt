package org.example

sealed class Cell {
    abstract fun evaluate(table: Table): Any?
    abstract fun display(): String

    class NumberCell(private val value: Double) : Cell() {
        override fun evaluate(table: Table) = value
        override fun display() = value.toString()
    }

    class TextCell(private val text: String) : Cell() {
        override fun evaluate(table: Table) = text
        override fun display() = "\"$text\""
    }

    class FormulaCell(private val formula: String) : Cell() {
        private var cached: Any? = null

        override fun evaluate(table: Table): Any? {
            if (cached != null) return cached

            val parts = formula.removePrefix("=").split('+').map { it.trim() }
            val values = parts.map { label ->
                table.getCellByLabel(label)?.evaluate(table)
            }

            cached = when {
                values.all { it is Double } -> (values[0] as Double) + (values[1] as Double)
                values.all { it is String } -> (values[0] as String) + (values[1] as String)
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
                input.startsWith("\"") && input.endsWith("\"") -> TextCell(input.removeSurrounding("\""))
                input.toDoubleOrNull() != null -> NumberCell(input.toDouble())
                else -> ErrorCell
            }
        }
    }
}

