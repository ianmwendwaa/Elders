package com.zion.elders.backend.local_database

import android.icu.text.NumberFormat
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import java.util.Locale

@Entity("zion_elders_table")
data class Member(
    @PrimaryKey val memberId: Int = 0,
    val memberName: String,
    val memberTelephone: String,
    val generatedMemberNumber: String
)

@Entity("weekly_meeting_data")
data class Meeting(
    @PrimaryKey(autoGenerate = true) val meetingId: Int = 0
)

@Entity("registered_projects")
data class Project(
    @PrimaryKey(autoGenerate = true) val projectId: Int = 0,
    val projectTitle: String,
    val projectTarget: Double

){
    val formattedContribution: String get() = NumberFormat.getInstance(Locale.US).format(0)
    val formattedTarget: String get() = NumberFormat.getInstance(Locale.US).format(projectTarget)
}