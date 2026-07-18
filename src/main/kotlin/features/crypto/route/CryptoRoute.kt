package com.behnamdev.features.crypto.route

import com.behnamdev.features.crypto.domain.service.GetCryptoUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.cryptoRouting() {
    val getCryptoUseCase by inject<GetCryptoUseCase>()


    get("/{symbol}") {
        val symbol = call.parameters["symbol"]

        if (symbol.isNullOrBlank()) {
            call.respond(HttpStatusCode.BadRequest, "Symbol is required")
            return@get
        }
        val result = getCryptoUseCase.execute(symbol)
        call.respond(result)
    }
}