package com.zion.elders.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zion.elders.screens.ProjectData
import com.zion.elders.ui.theme.cardTitle
import com.zion.elders.ui.theme.errorOrPendingColor
import com.zion.elders.ui.theme.moneyBoxColor
import com.zion.elders.ui.theme.progressBarTrackColor

@Composable
fun ProjectCardData(
    projectData: ProjectData
){
    val projectProgress = projectData.amountContributed / projectData.projectTarget
    val completionProgress = (projectProgress * 100 == 100.0f)

    SystemForm(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 7.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = projectData.projectTitle, fontSize = 15.sp, color = cardTitle, fontWeight = FontWeight.SemiBold)
            Box(
                modifier = Modifier.width(65.dp).height(30.dp).clip(RoundedCornerShape(10.dp)).background(
                    if (completionProgress) moneyBoxColor else errorOrPendingColor
                ),
                contentAlignment = Alignment.Center
            ){
                Text(text = "${projectProgress*100}%", fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                    color = if (completionProgress) cardTitle else Color.White
                )
            }
        }
        LinearProgressIndicator(
            progress = { projectProgress },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            color = cardTitle,
            trackColor = progressBarTrackColor,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap
        )
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = "Ksh.${projectData.formattedContribution}", color = cardTitle, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                Text(text = " contributed", color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Normal)
            }
            Row {
                Text(text = "Target: ", color = Color.Gray, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                Text(text = "Ksh.${projectData.formattedTarget}", color = Color.Gray, fontSize = 15.sp, fontWeight = FontWeight.Normal)
            }
        }
    }
}