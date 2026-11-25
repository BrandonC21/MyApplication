
package com.example.myapplication.data.remote.controller

import com.example.myapplication.config.ConexionAPI
import com.example.myapplication.data.remote.dto.ProductoFarmaciaDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApifyFarmaciaController(
    private val httpClient: HttpClient
) {

    suspend fun obtenerProductosFarmacia(
        query: String
    ): List<ProductoFarmaciaDTO> {

        val bodyPayload = if (query.isNotBlank()) {
            mapOf("query" to query)
        } else {
            emptyMap<String, String>()
        }

        val response = httpClient.post(ConexionAPI.urlData()) {

            contentType(ContentType.Application.Json)

            parameter("limit", 8)

            setBody(bodyPayload)
        }

        println("Código HTTP: ${response.status}")

        if (!response.status.value.let { it in 200..299 }) {
            val errorBody = response.bodyAsText()

            throw Exception(
                "Error de Apify: ${response.status} - $errorBody"
            )
        }

        return response.body()
    }
}
