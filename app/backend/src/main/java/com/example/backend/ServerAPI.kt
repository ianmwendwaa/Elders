package com.example.backend

import com.example.backend.models.ReceivedMemberData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlin.collections.emptyList

class ServerAPI {
    private val client = HttpClient(CIO){
        install(ContentNegotiation){
            json()
        }
    }
    suspend fun getAllMembers(): List<ReceivedMemberData>{
        return try {
            val response = client.get("http://127.0.0.1:8080/all_members")

            if (response.status == HttpStatusCode.OK) {
                response.body<List<ReceivedMemberData>>()
            } else {
                emptyList()
            }
        }catch (e: Exception){
            println("Encountered error: ${e.message}")
            emptyList()
        }
    }
}