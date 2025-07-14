package org.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TableDto(
    val grid: List<List<CellDto>>
)