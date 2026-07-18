package com.behnamdev.features.cryptolist.presentation.route

import com.behnamdev.features.crypto.domain.service.GetCryptoUseCase
import com.behnamdev.features.cryptolist.domain.service.GetCryptoListUseCase
import io.ktor.http.*
import io.ktor.server.http.content.resolveResource // ایمپورت درست برای پیدا کردن فایل
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.concurrent.ConcurrentHashMap
import kotlin.getValue

data class CacheEntry(val data: Any, val timestamp: Long)

fun Route.cryptoListRouting() {
    val getCryptoListUseCase by inject<GetCryptoListUseCase>()
    val getCryptoUseCase by inject<GetCryptoUseCase>()

    val cryptoListCache = ConcurrentHashMap<String, CacheEntry>()
    val cryptoDetailCache = ConcurrentHashMap<String, CacheEntry>()

    val LIST_CACHE_DURATION = 5 * 60 * 1000
    val DETAIL_CACHE_DURATION = 2 * 60 * 1000

    // ۱. صفحه اصلی داشبورد
    get("/") {
        // پیدا کردن فایل و ارسال آن به مرورگر
        val resource = call.resolveResource("static/index.html")
        if (resource != null) {
            call.respond(resource)
        } else {
            call.respond(HttpStatusCode.NotFound, "index.html not found in resources/static/")
        }
    }

    // ۲. API دریافت لیست کلی ارزها
    get("/cryptoList") {
        val cacheKey = "all_crypto_list"
        val cached = cryptoListCache[cacheKey]
        val currentTime = System.currentTimeMillis()

        if (cached != null && (currentTime - cached.timestamp) < LIST_CACHE_DURATION) {
            call.respond(cached.data)
            return@get
        }

        try {
            val result = getCryptoListUseCase.execute()
            cryptoListCache[cacheKey] = CacheEntry(result, currentTime)
            call.respond(result)
        } catch (e: Exception) {
            if (cached != null) {
                call.respond(cached.data)
            } else {
                call.respond(HttpStatusCode.GatewayTimeout, mapOf("error" to "External API Timeout"))
            }
        }
    }

    // ۳. صفحه نمایش جزئیات کامل ارز
    get("/details/{symbol}") {
        val symbol = call.parameters["symbol"] ?: ""

        if (symbol.equals("favicon.ico", ignoreCase = true) || symbol.isBlank()) {
            call.respond(HttpStatusCode.NotFound)
            return@get
        }

        // پیدا کردن فایل جزئیات و ارسال آن به مرورگر
        val resource = call.resolveResource("static/detail.html")
        if (resource != null) {
            call.respond(resource)
        } else {
            call.respond(HttpStatusCode.NotFound, "detail.html not found in resources/static/")
        }
    }

    // ۴. API دریافت دیتای تکی ارز دیجیتال
    get("/api/{symbol}") {
        val symbol = call.parameters["symbol"] ?: ""

        if (symbol.equals("favicon.ico", ignoreCase = true) || symbol.isBlank()) {
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Invalid symbol"))
            return@get
        }

        val cached = cryptoDetailCache[symbol]
        val currentTime = System.currentTimeMillis()

        if (cached != null && (currentTime - cached.timestamp) < DETAIL_CACHE_DURATION) {
            call.respond(cached.data)
            return@get
        }

        try {
            val result = getCryptoUseCase.execute(symbol = symbol)
            if (result.isNotEmpty()) {
                cryptoDetailCache[symbol] = CacheEntry(result[0], currentTime)
                call.respond(result[0])
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to "Asset not found"))
            }
        } catch (e: Exception) {
            if (cached != null) {
                call.respond(cached.data)
            } else {
                call.respond(HttpStatusCode.GatewayTimeout, mapOf("error" to "External API Timeout"))
            }
        }
    }
}