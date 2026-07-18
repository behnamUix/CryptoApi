package com.behnamdev.features.crypto.data.repository

import com.behnamdev.features.crypto.data.remote.CryptoRemoteDataSource
import com.behnamdev.features.crypto.domain.repository.CryptoRepository
import com.behnamdev.features.crypto.domain.model.CryptoItem

class CryptoRepositoryImpl(private val remoteDataSource: CryptoRemoteDataSource) : CryptoRepository {
    override suspend fun fetchRemoteCrypto(): List<CryptoItem> {
        return try {
            val responseDto = remoteDataSource.fetchLiveCryptoList()

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