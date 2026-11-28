package com.zion.elders.models.projects_data

import android.icu.text.NumberFormat
import java.util.Locale

// Individual data attributes for the project cards to be populated on the projects' screen
data class ProjectData(
    val projectTitle: String,
    val amountContributed: Double,
    val projectTarget: Double,
){
    val formattedContribution: String get() = NumberFormat.getInstance(Locale.US).format(amountContributed)
    val formattedTarget: String get() = NumberFormat.getInstance(Locale.US).format(projectTarget)
}