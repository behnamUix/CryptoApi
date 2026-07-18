package com.behnamdev.features.cryptolist.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CryptoResponse(
    val status: Boolean,
    val resultset_size: Int,
    val result: List<CryptoItem>
)

@Serializable
data class CryptoItem(
    val id: String,
    val symbol: String,
    val name: String,
    val source: String,
    val ohlc_available_from: String,
    val history_available_from: String
)

