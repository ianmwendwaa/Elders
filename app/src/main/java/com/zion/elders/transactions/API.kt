package com.zion.elders.transactions

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class API {
    private val getMembersEndpoint = "http://192.168.8.104:8080/all_members"
    private val client = HttpClient(CIO){
        install(ContentNegotiation){
            json(
                Json{
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }
    @Serializable
    data class Payload(
        @SerialName("member_name") val name: String,
        @SerialName("member_phone_number") val phoneNumber: String,
        @SerialName("member_number") val number: String
    )
    suspend fun fetchMembers(): List<Payload> {
        // Divine intellectual intervention. Praise Jehovah!
        val resp = json.decodeFromString<Payload>(client.get(getMembersEndpoint).bodyAsText())
        return client.get(getMembersEndpoint).body<List<Payload>>()
    }
}