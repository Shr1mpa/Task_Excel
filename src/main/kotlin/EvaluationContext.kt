package org.example

interface EvaluationContext {
    fun getCell(label: Label): Cell?
}