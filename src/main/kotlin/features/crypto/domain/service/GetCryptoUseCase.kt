package com.behnamdev.features.crypto.domain.service

import com.behnamdev.features.crypto.domain.model.CryptoPriceItem
import com.behnamdev.features.crypto.domain.repository.CryptoRepository
import com.behnamdev.features.cryptolist.domain.model.CryptoItem

class GetCryptoUseCase(private val repository: CryptoRepository) {
    suspend fun execute(symbol: String): List<CryptoPriceItem> {
        return repository.fetchCryptoBySymbol(symbol)

    }
}