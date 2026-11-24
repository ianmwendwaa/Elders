package com.zion.elders.backend.local_database

import androidx.room3.Entity
import androidx.room3.PrimaryKey

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
)