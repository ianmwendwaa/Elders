package com.zion.elders.transactions

import android.util.Log
import com.zion.elders.models.member_data.MemberPayload
import com.zion.elders.resultdump.Result
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
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class API {
    private val getMembersEndpoint = "http://192.168.8.104:8080/all_members"
    private val postMemberEndpoint = "http://192.168.8.104:8080/create_member"
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

    @Serializable
    data class Payload(
        @SerialName("member_name") val name: String,
        @SerialName("member_phone_number") val phoneNumber: String,
        @SerialName("member_number") val number: String
    )
    suspend fun fetchMembers(): List<Payload> {
        // Divine intellectual intervention. Praise Jehovah!
        return client.get(getMembersEndpoint).body<List<Payload>>()
    }

    suspend fun createMember(memberPayload: MemberPayload): Result {
        val response = client.post(postMemberEndpoint){
            setBody(memberPayload)
            contentType(ContentType.Application.Json)
        }

        if (response.status != HttpStatusCode.OK){
            Log.e("MEMBER_CREATION", "Status code response: ${response.status}")
            Log.e("MEMBER_CREATION", "Actual response body: ${response.bodyAsText()}")
            return Result.ERROR
        }
        Log.w("MEMBER_CREATION", "Successfully conveyed member payload: ${response.bodyAsText()}")
        return Result.SUCCESS
    }
}