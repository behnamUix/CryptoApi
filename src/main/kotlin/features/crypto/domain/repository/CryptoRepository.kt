package com.behnamdev.features.crypto.domain.repository

import com.behnamdev.features.crypto.domain.model.CryptoItem

interface CryptoRepository {
    suspend fun fetchRemoteCrypto(): List<CryptoItem>
}

