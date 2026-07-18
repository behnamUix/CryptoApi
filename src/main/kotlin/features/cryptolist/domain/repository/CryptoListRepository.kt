package com.behnamdev.features.cryptolist.domain.repository

import com.behnamdev.features.cryptolist.domain.model.CryptoItem

interface CryptoListRepository {
    suspend fun fetchRemoteCrypto(): List<CryptoItem>

}

