package org.example.presentation

import org.example.data.ServerClient
import org.example.data.mapper.toDomain
import org.example.data.mapper.toDto
import org.example.domain.logic.TableUtils
import org.example.domain.model.Table

class TableController {
    private var currentTable: Table? = null

    fun createNewTable() {
        currentTable = TableUtils.build()
    }

    fun evaluateTable() {
        currentTable?.let { TableUtils.evaluate(it) }
    }

    fun printTable() {
        currentTable?.let { TableUtils.print(it, "Поточна таблиця") }
    }

    suspend fun loadFromServer(): Boolean {
        val dto = ServerClient.load()
        return if (dto != null) {
            currentTable = dto.toDomain()
            true
        } else false
    }

    suspend fun saveToServer(): Boolean {
        val table = currentTable ?: return false
        return ServerClient.save(table.toDto())
    }

    fun hasTable(): Boolean = currentTable != null
}