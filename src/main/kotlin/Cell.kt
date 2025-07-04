package org.example

sealed class Cell {
    abstract fun evaluate(): Any?
    abstract fun display(): String

    class ValueCell<T>(private val value: T) : Cell() {
        override fun evaluate() = value
        override fun display(): String = when (value) {
            is String -> "\"$value\""
            else -> value.toString()
        }
    }

    class FormulaCell(
        private val formula: String,
        private val context: EvaluationContext
    ) : Cell() {
        private var cached: Any? = null

        override fun evaluate(): Any? {
            if (cached != null) return cached

            val values = FormulaParser.parse(formula) { label -> context.getCell(label) }

            if (values.any { it == "!err" }) {
                cached = "!err"
                return cached
            }

            cached = when {
                values.all { it is Double } -> values.sumOf { it as Double }.toString()
                values.all { it is String } -> values.joinToString("") { it as String }
                else -> "!err"
            }

            return cached
        }

        override fun display(): String = cached?.toString() ?: formula
    }

    object ErrorCell : Cell() {
        override fun evaluate() = "!err"
        override fun display() = "!err"
    }

    companion object {
        fun fromInput(input: String, context: EvaluationContext): Cell {
            return when {
                input.startsWith("=") -> FormulaCell(input, context)
                input.startsWith("\"") && input.endsWith("\"") -> ValueCell(input.removeSurrounding("\""))
                input.toDoubleOrNull() != null -> ValueCell(input.toDouble())
                else -> ErrorCell
            }
        }
    }
}