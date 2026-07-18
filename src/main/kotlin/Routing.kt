package com.behnamdev


import com.behnamdev.features.crypto.presentation.route.cryptoRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        cryptoRouting()

    }
}