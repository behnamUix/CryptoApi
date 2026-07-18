package com.behnamdev.features.crypto.data.remote.dto

import com.behnamdev.features.crypto.domain.model.CryptoItem
import kotlinx.serialization.Serializable

@Serializable
data class CryptoResponseDto(
    val status: Boolean,
    val resultset_size: Int,
    val result: List<CryptoItem>
)

@Serializable
data class CryptoItemDto(
    val id: String,
    val symbol: String,
    val name: String,
    val source: String,
    val ohlc_available_from: String,
    val history_available_from: String

)