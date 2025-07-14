package org.example.data.dto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class CellDto {
    @Serializable @SerialName("value")
    data class Value(val value: String) : CellDto()

    @Serializable @SerialName("number")
    data class Number(val number: Double) : CellDto()

    @Serializable @SerialName("formula")
    data class Formula(val formula: String) : CellDto()

    @Serializable @SerialName("error")
    object Error : CellDto()
}