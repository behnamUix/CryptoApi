package com.behnamdev.features.crypto.data.remote

import com.behnamdev.features.crypto.data.remote.dto.CryptoResponseDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class CryptoRemoteDataSource(private val client: HttpClient) {

    val apiUrl = "https://api.freecryptoapi.com/v1/getCryptoList"
    val apiToken = "x8fv4264uf8emri7a016"

    suspend fun fetchLiveCryptoList(): CryptoResponseDto {
        // ارسال درخواست مستقیم به API خارجی همراه با هدر Authorization
        return client.get(apiUrl) {
            header(HttpHeaders.Authorization, "Bearer $apiToken")
            contentType(ContentType.Application.Json)
        }.body() // تبدیل خودکار پاسخ JSON به Dto ما
    }
}