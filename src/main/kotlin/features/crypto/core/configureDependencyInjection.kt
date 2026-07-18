package com.behnamdev.features.crypto.core

import com.behnamdev.features.crypto.di.appModules
import com.behnamdev.features.crypto.di.networkModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.ktor.plugin.koinModules

fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(appModules,networkModule)
    }
}