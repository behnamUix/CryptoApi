package com.behnamdev.features.crypto.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CryptoPriceResponseDto(
    val status: String,
    val symbols: List<CryptoPriceItemDto>
)

@Serializable
data class CryptoPriceItemDto(
    val symbol: String,
    val last: String,
    val last_btc: String,
    val lowest: String,
    val highest: String,
    val date: String,
    val daily_change_percentage: String,
    val source_exchange: String
)