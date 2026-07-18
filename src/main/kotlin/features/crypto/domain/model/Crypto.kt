package com.behnamdev.features.crypto.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CryptoPriceResponse(
    val status: String,
    val symbols: List<CryptoPriceItem>
)

@Serializable
data class CryptoPriceItem(
    val symbol: String,
    val last: String,
    val last_btc: String,
    val lowest: String,
    val highest: String,
    val date: String,
    val daily_change_percentage: String,
    val source_exchange: String
)