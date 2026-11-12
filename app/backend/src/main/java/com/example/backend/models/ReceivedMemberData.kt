package com.example.backend.models

import com.example.core.dto.TableModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReceivedMemberData(
    @SerialName("sent_member_name") val name: String,
    @SerialName("sent_member_phone_number") val phone: String,
    @SerialName("sent_member_number") val num: String): TableModel