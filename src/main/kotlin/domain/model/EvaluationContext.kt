package org.example.domain.model

interface EvaluationContext {
    fun getCell(label: Label): Cell?
}