package com.behnamdev.features.crypto.data.remote

import com.behnamdev.features.crypto.data.remote.dto.CryptoPriceResponseDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class CryptoRemoteDataSource(private val client: HttpClient) {

    val apiUrl = "https://api.freecryptoapi.com/v1/getData"
    val apiToken = "x8fv4264uf8emri7a016"

    suspend fun fetchCrypto(symbol: String): CryptoPriceResponseDto {
        return client.get(apiUrl) {
            header(HttpHeaders.Authorization, "Bearer $apiToken")
            contentType(ContentType.Application.Json)
            parameter("symbol", symbol)
        }.body()
    }
}