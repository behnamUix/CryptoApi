package com.behnamdev.features.crypto.di

import com.behnamdev.features.crypto.data.remote.CryptoRemoteDataSource
import com.behnamdev.features.crypto.data.repository.CryptoRepositoryImpl
import com.behnamdev.features.crypto.domain.repository.CryptoRepository
import com.behnamdev.features.crypto.domain.service.GetCryptoListUseCase
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation as ClientContentNegotiation

val networkModule = module {
    single {
        HttpClient(CIO) {
            install(ClientContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }
}
val appModules = module {
    single<CryptoRepository> { CryptoRepositoryImpl(get()) }
    single { GetCryptoListUseCase(get()) }
    single { CryptoRemoteDataSource(get()) }
    // UseCase
    single {
        GetCryptoListUseCase(get())
    }
}