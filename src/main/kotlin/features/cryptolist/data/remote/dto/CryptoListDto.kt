package com.behnamdev.features.cryptolist.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CryptoResponseDto(
    val status: Boolean,
    val resultset_size: Int,
    val result: List<CryptoItemDto>
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

