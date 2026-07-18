package com.behnamdev.features.crypto.core

import com.behnamdev.di.appModules
import com.behnamdev.di.networkModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(appModules,networkModule)
    }
}