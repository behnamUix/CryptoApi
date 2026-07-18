package com.behnamdev

import com.behnamdev.features.crypto.core.configureDependencyInjection
import com.behnamdev.features.crypto.core.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.cio.EngineMain.main(args)



}
fun Application.module() {
    // ۱. ابتدا کوین را استارت میکنیم
    configureDependencyInjection()

    // ۲. سریالایزیشن (JSON) را لود میکنیم
    configureSerialization()

    // ۳. روت‌ها را در آخرین مرحله لود میکنیم
    configureRouting()
}
