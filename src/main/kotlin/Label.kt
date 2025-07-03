package org.example

data class Label(val row: Int, val col: Int) {
    companion object {
        fun fromString(input: String): Label? {
            val match = Regex("([A-Z])(\\d+)").matchEntire(input.uppercase()) ?: return null
            val col = match.groupValues[1][0] - 'A'
            val row = match.groupValues[2].toIntOrNull()?.minus(1) ?: return null
            return Label(row, col)
        }

        fun toString(row: Int, col: Int): String {
            return "${('A' + col)}${row + 1}"
        }
    }
}