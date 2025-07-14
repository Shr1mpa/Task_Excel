package org.example.data.mapper

import org.example.domain.model.Cell
import org.example.domain.model.EvaluationContext
import org.example.data.dto.CellDto

fun CellDto.toDomain(context: EvaluationContext): Cell = when (this) {
    is CellDto.Value -> Cell.ValueCell(this.value)
    is CellDto.Number -> Cell.ValueCell(this.number)
    is CellDto.Formula -> Cell.FormulaCell(this.formula, context)
    is CellDto.Error -> Cell.ErrorCell
}

fun Cell.toDto(): CellDto = when (this) {
    is Cell.ValueCell<*> -> when (val v = this.evaluate()) {
        is String -> CellDto.Value(v)
        is Number -> CellDto.Number(v.toDouble())
        else -> CellDto.Error
    }

    is Cell.FormulaCell -> CellDto.Formula(this.display())
    is Cell.ErrorCell -> CellDto.Error
}