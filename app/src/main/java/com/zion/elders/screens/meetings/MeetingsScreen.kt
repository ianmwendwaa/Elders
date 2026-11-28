package com.zion.elders.screens.meetings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.zion.elders.components.home.MeetingDataCard
import com.zion.elders.models.MeetingContributionsBreakdown
import com.zion.elders.models.MeetingData
import com.zion.elders.navigation.ROUTE_CREATE_MEETING_ENTRY
import com.zion.elders.ui.theme.experimentalBackground
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MeetingsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        val meetingList = listOf(
            MeetingData("First meeting",
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                500,
                listOf(
                    MeetingContributionsBreakdown("Ian Mwendwa", 50),
                    MeetingContributionsBreakdown("Victor Nzomo", 50),
                    MeetingContributionsBreakdown("Ian Mwendwa", 50)
                ),
                "Contribution of Ksh.1500 by each member towards purchasing a keyboard."),
            MeetingData("Second meeting",
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                500,
                listOf(
                    MeetingContributionsBreakdown("Ian Mwendwa", 50),
                    MeetingContributionsBreakdown("Victor Nzomo", 50),
                    MeetingContributionsBreakdown("Ian Mwendwa", 50)
                ),
                "Contribution of Ksh.1500 by each member towards purchasing a keyboard.")
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = "Meeting Logs", fontWeight = FontWeight.Bold, fontSize = 19.sp, color = Color.Black)
            Button(
                onClick = {
                    navController.navigate(ROUTE_CREATE_MEETING_ENTRY)
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.width(110.dp).height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = experimentalBackground
                )
            ) {
                Text(text = "New Entry", fontSize = 14.sp, color = Color.White)
            }
        }
        meetingList.forEach { data ->
            MeetingDataCard(data)
        }
    }
}