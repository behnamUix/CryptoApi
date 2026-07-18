package com.behnamdev.features.crypto.domain.repository

import com.behnamdev.features.crypto.domain.model.CryptoPriceItem

interface CryptoRepository {
    suspend fun fetchCryptoBySymbol(symbol: String): List<CryptoPriceItem>

}

