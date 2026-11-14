package com.zion.elders.models

import com.zion.core.dto.TableModel

data class MeetingContributionsBreakdown(
    val contributorsName: String,
    val contribution: Int
)
data class MeetingData(
    val title: String,
    val date: String,
    val totalContribution: Int,
    val breakdown: List<MeetingContributionsBreakdown>,
    val emergingIssues: String
) : TableModel
