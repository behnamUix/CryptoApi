package com.behnamdev.features.cryptolist.data.repository

import com.behnamdev.features.cryptolist.data.remote.CryptoListRemoteDataSource
import com.behnamdev.features.cryptolist.domain.model.CryptoItem
import com.behnamdev.features.cryptolist.domain.repository.CryptoListRepository

class CryptoListRepositoryImpl(private val cryptoListRemoteDataSource: CryptoListRemoteDataSource) : CryptoListRepository {
    override suspend fun fetchRemoteCrypto(): List<CryptoItem> {
        return try {
            val responseDto = cryptoListRemoteDataSource.fetchLiveCryptoList()

            // تبدیل لایه دیتا (Dto) به لایه دومین (Model خالص)
            responseDto.result.map { dto ->
                CryptoItem(
                    id = dto.id,
                    name = dto.name,
                    symbol = dto.symbol,
                    source = dto.source,
                    ohlc_available_from = dto.ohlc_available_from,
                    history_available_from = dto.history_available_from

                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}