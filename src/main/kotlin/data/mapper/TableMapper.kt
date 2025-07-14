package org.example.data.mapper

import org.example.domain.model.Table
import org.example.data.dto.TableDto

fun TableDto.toDomain(): Table {
    val rows = grid.size
    val cols = grid.firstOrNull()?.size ?: 0
    val table = Table(rows, cols)
    grid.forEachIndexed { row, line ->
        line.forEachIndexed { col, cellDto ->
            val cell = cellDto.toDomain(table)
            table.setCell(row, col, cell)
        }
    }
    return table
}

fun Table.toDto(): TableDto {
    val grid = List(rows) { r ->
        List(cols) { c ->
            getCell(r, c).toDto()
        }
    }
    return TableDto(grid)
}