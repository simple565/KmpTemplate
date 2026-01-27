package com.kmp.template.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * @author Lian
 * @date 2026/1/2
 */
class Network(private val config: NetworkConfig = NetworkConfig()) {

    private val client = HttpClient(CIO) {
        if (config.enableJsonParsing) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                    explicitNulls = false
                })
            }
        }

        if (config.enableLogging) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }

        install(HttpCache)

        install(HttpTimeout) {
            requestTimeoutMillis = config.timeoutMillis
            connectTimeoutMillis = 10_000
            socketTimeoutMillis = 10_000
        }

        defaultRequest {
            if (config.baseUrl.isNotBlank()) {
                url(config.baseUrl)
            }
            config.defaultHeaders.forEach { (key, value) ->
                headers.append(key, value)
            }
            contentType(ContentType.Application.Json)
        }
    }

    suspend fun get(path: String, params: Map<String, String> = emptyMap()): HttpResponse {
        return client.get(path) {
            url {
                params.forEach {
                    parameters.append(it.key, it.value)
                }
            }
        }
    }
}

data class NetworkConfig(
    val baseUrl: String = "",
    val timeoutMillis: Long = 30_000,
    val enableLogging: Boolean = true,
    val enableJsonParsing: Boolean = true,
    val defaultHeaders: Map<String, String> = emptyMap()
) {
    companion object {
        val userAgentHeader = mapOf(
            "User-Agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3"
        )
    }
}