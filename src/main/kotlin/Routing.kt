package com.behnamdev


import com.behnamdev.features.crypto.route.cryptoRouting
import com.behnamdev.features.cryptolist.presentation.route.cryptoListRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        cryptoRouting()
        cryptoListRouting()

    }
}