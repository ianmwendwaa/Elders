package com.zion.elders.server_init

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ServerInit {
    private val client = HttpClient(CIO){
        install(ContentNegotiation){
            json()
        }
    }
    @Test
    fun test_server_conn(){
        // Expect OK, with body "Hello Ian! Seeing this message means that Mirage is up and ready to get her hands dirty."
        runBlocking {
            val response = client.get("http://127.0.0.1:8080/")
            assertEquals(HttpStatusCode.OK, response.status)
            println("Response: ${response.bodyAsText()}")
        }
    }
}