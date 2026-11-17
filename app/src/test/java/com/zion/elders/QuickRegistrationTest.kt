package com.zion.elders

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.junit.Test


class QuickRegistrationTest {
    private val client = HttpClient(CIO){
        install(ContentNegotiation){
            json()
        }
    }
    @Serializable
    private data class Payload(
        @SerialName("member_name") val name: String,
        @SerialName("member_phone_number") val phoneNumber: String,
        @SerialName("member_number") val number: String
    )
    @Test
    fun register_member(){
        val mockPayload = Payload(
            name = "Ian Mwendwa",
            phoneNumber = "0791930433",
            number = "ELDR-IANM-0433"
        )
        runBlocking {
            val response = client.post("http://127.0.0.1:8080/create_member"){
                contentType(ContentType.Application.Json)
                setBody(mockPayload)
            }
            assertEquals(HttpStatusCode.OK, response.status)
            println("Mirage response: ${response.bodyAsText()}")
        }
    }
    @Test
    fun get_members(){
        runBlocking {
            val response = client.get("http://127.0.0.1:8080/all_members")
            assertEquals(HttpStatusCode.OK, response.status)

            val memberList = response.body<List<Payload>>()
            println("Response body: $memberList")
        }
    }
    // To be used on the home overview card
    @Test
    fun member_count(){
        runBlocking {
            val response = client.get("http://127.0.0.1:8080/all_members")
            assertEquals(HttpStatusCode.OK, response.status)

            val members = response.body<List<Payload>>()
            println("Total members: ${members.size}")
        }
    }
}