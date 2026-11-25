package com.example.myapplication.config

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ConexionAPI{
    const val APIFY_TOKEN = "PONER_TOKEN"
    const val ID = "latinamericadata~farmacias-del-ahorro-mexico"

    fun urlData(): String =
        "https://api.apify.com/v2/acts/$ID/run-sync-get-dataset-items?token=$APIFY_TOKEN"

    val httpClient: HttpClient by lazy {
        HttpClient{
            install(ContentNegotiation){
                json(
                    Json{
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 120_000L
                connectTimeoutMillis = 30_000L
                socketTimeoutMillis = 120_000L
            }
        }
    }
}