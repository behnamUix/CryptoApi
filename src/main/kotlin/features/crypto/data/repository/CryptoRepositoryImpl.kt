package com.behnamdev.features.crypto.data.repository

import com.behnamdev.features.crypto.data.remote.CryptoRemoteDataSource
import com.behnamdev.features.crypto.domain.repository.CryptoRepository
import com.behnamdev.features.crypto.domain.model.CryptoPriceItem

class CryptoRepositoryImpl(private val remoteDataSource: CryptoRemoteDataSource) : CryptoRepository {
    override suspend fun fetchCryptoBySymbol(symbol: String): List<CryptoPriceItem> {
        return try {
            val cryptoResponseDto = remoteDataSource.fetchCrypto(symbol)

            // تبدیل لایه دیتا (Dto) به لایه دومین (Model خالص)
            cryptoResponseDto.symbols.map { dto ->
                CryptoPriceItem(
                    symbol = dto.symbol,
                    last = dto.last,
                    last_btc = dto.last_btc,
                    lowest = dto.lowest,
                    highest = dto.highest,
                    date = dto.date,
                    daily_change_percentage = dto.daily_change_percentage,
                    source_exchange = dto.source_exchange,


                    )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}