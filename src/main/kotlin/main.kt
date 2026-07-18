package com.behnamdev

import com.behnamdev.features.crypto.core.configureDependencyInjection
import com.behnamdev.features.crypto.core.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer

fun main(args: Array<String>) {
    io.ktor.server.cio.EngineMain.main(args)




}
fun Application.module() {

    configureDependencyInjection()


    configureSerialization()


    configureRouting()


}
