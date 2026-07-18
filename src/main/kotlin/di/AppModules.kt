package com.behnamdev.di

import com.behnamdev.features.crypto.data.remote.CryptoRemoteDataSource
import com.behnamdev.features.crypto.data.repository.CryptoRepositoryImpl
import com.behnamdev.features.crypto.domain.repository.CryptoRepository
import com.behnamdev.features.crypto.domain.service.GetCryptoUseCase
import com.behnamdev.features.cryptolist.data.remote.CryptoListRemoteDataSource
import com.behnamdev.features.cryptolist.data.repository.CryptoListRepositoryImpl
import com.behnamdev.features.cryptolist.domain.repository.CryptoListRepository
import com.behnamdev.features.cryptolist.domain.service.GetCryptoListUseCase
import io.ktor.client.*
import io.ktor.client.engine.okhttp.* // این خط را اضافه کنید
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation as ClientContentNegotiation

val networkModule = module {
    single {
        HttpClient(OkHttp) {
            install(ClientContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }
}
val appModules = module {
    //cryptoList
    single { CryptoListRemoteDataSource(get()) }
    single<CryptoListRepository> { CryptoListRepositoryImpl(get()) }
    single { GetCryptoUseCase(get()) }
    single { GetCryptoListUseCase(get()) }

    //crypto
    single { CryptoRemoteDataSource(get()) }
    single<CryptoRepository> { CryptoRepositoryImpl(get()) }
    single { GetCryptoUseCase(get()) }
    single { CryptoRemoteDataSource(get()) }
}