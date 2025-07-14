package org.example.data

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import org.example.data.dto.TableDto

object ServerClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun save(tableDto: TableDto): Boolean {
        return try {
            client.post("http://localhost:8080/table") {
                contentType(ContentType.Application.Json)
                setBody(tableDto)
            }
            println("Таблицю збережено")
            true
        } catch (e: Exception) {
            println("Не вдалося зберегти таблицю на сервер: ${e.message}")
            false
        }
    }

    suspend fun load(): TableDto? {
        return try {
            client.get("http://localhost:8080/table").body()
        } catch (e: Exception) {
            println("Не вдалося підключитися до сервера: ${e.message}")
            null
        }
    }
}