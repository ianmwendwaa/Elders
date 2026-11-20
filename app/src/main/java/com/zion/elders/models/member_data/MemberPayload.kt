package com.zion.elders.models.member_data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberPayload(
    @SerialName("entered_name") val memberInputName: String,
    @SerialName("entered_phone_number") val memberTelephone: String,
    @SerialName("generated_member_number") val memberNumber: String
)
