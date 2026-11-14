package com.zion.elders.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.zion.elders.R
import com.zion.elders.components.backgroundColor
import com.zion.elders.components.cardColor
import com.zion.elders.components.textColor
import com.zion.elders.models.MeetingContributionsBreakdown
import com.zion.elders.models.MeetingData
import com.zion.elders.ui.theme.cardTitle
import com.zion.elders.ui.theme.experimentalBackground
import com.zion.elders.ui.theme.moneyBoxColor
import com.zion.elders.ui.theme.moneyTextColor
import com.zion.elders.ui.theme.screenBackground
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(navController: NavHostController){
    val totalMembers by remember { mutableIntStateOf(0) }
    val totalContribution by remember { mutableIntStateOf(0) }

    var qRegMemberName by remember { mutableStateOf("") }
    var qRegPhoneNumber by remember { mutableStateOf("") }

    val dummyMeetingData = MeetingData(
        "First meeting",
        LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
        750,
        listOf(
            MeetingContributionsBreakdown("Ian Mwendwa", 50),
            MeetingContributionsBreakdown("Ian Mwendwa", 50),
            MeetingContributionsBreakdown("Ian Mwendwa", 50)
        ),
        "Some emerging issues as discussed"
    )
    Column(
//        0xFFEFE6DC
        modifier = Modifier.fillMaxSize().background(screenBackground).verticalScroll(rememberScrollState()),
    ) {
        Text(text = "Hello Ian!", fontSize = 19.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(start = 16.dp, top = 50.dp, end = 16.dp),
            color = Color.Black
        )

        // Overview data
        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            OverviewDataCard(
                "Total Roster",
                R.drawable.ic_right_button,
                totalMembers.toString(),
                "Verified Active"
            )

            OverviewDataCard(
                "Contributions",
                R.drawable.ic_right_button,
                "Ksh.$totalContribution",
                "Verified Active"
            )

        }

        // Quick Register Member
        SystemForm(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(R.drawable.ic_right_button), "quick_reg_mem")
                Text(text = "Quick Register Member", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = cardTitle)
            }

            Spacer(modifier = Modifier.height(1.dp).border(0.1.dp, color = Color.Gray))

            TextField(
                value = qRegMemberName,
                onValueChange = {
                    qRegMemberName = it
                },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                placeholder = {
                    Text("Full name", fontSize = 14.sp)
                },
                textStyle = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent, // Hides border on focus
                    unfocusedIndicatorColor = Color.Transparent, // Hides border when idle
                ),
                shape = RoundedCornerShape(7.dp)
            )

            TextField(
                value = qRegPhoneNumber,
                onValueChange = {
                    qRegPhoneNumber = it
                },
                modifier = Modifier.width(150.dp).height(48.dp),
                placeholder = {
                    Text("Phone No.", fontSize = 14.sp)
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberSigned,
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color(0xFFF5F5F5),
                    focusedIndicatorColor = Color.Transparent, // Hides border on focus
                    unfocusedIndicatorColor = Color.Transparent, // Hides border when idle
                ),
                shape = RoundedCornerShape(7.dp)
            )

            Button(onClick = {},
                modifier = Modifier.width(100.dp).height(45.dp).align(Alignment.End),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = experimentalBackground
                )
            ) {
                Text(text = "Register", color = Color.White, fontSize = 14.sp)
            }
        }

        // Recent meetings
        Row(modifier = Modifier.fillMaxWidth().padding(
            start = 16.dp, end = 16.dp, bottom = 1.dp, top = 10.dp
        ), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Recent meetings", color = cardTitle, fontWeight = FontWeight.SemiBold)
            Text(text = "See all", color = experimentalBackground, fontSize = 14.sp)
        }
        MeetingDataCard(dummyMeetingData)
    }
}

@Composable
fun OverviewDataCard(
    dataLabel: String,
    dataPainterResource: Int,
    dataValue: String,
    dataSecondaryLabel: String = ""
){
    Card(
        modifier = Modifier.width(150.dp),
        shape = RoundedCornerShape(11.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF4F4F4)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = dataLabel, fontSize = 13.sp, color = Color.Gray)
                Image(
                    painter = painterResource(dataPainterResource),
                    contentDescription = "roster_img"
                )
            }
            Text(text = dataValue, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

            // This may or may not be included, it's up to me to decide
            Text(text = dataSecondaryLabel, fontSize = 12.sp, color = Color.Gray)

        }
    }
}

@Composable
fun SystemForm(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEFECE7)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp),
            content = content
        )
    }
}

@Composable
fun MeetingDataCard(
    meetingData: MeetingData
){
    SystemForm(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = meetingData.title, color = cardTitle, fontWeight = FontWeight.SemiBold, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Box(modifier = Modifier.width(70.dp).height(35.dp).clip(RoundedCornerShape(8.dp)).background(moneyBoxColor),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Ksh.${meetingData.totalContribution}",
                    fontSize = 14.sp, color = moneyTextColor, fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            }
        }

        Text(text = meetingData.date, fontWeight = FontWeight.SemiBold, color = Color.Gray, fontSize = 14.sp)

        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEFE3D7)
        )) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(text = "Breakdown", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.Gray)

                meetingData.breakdown.forEach { (contributorsName, contribution) ->
                    ContributionsListItem(contributorsName, contribution)
                }
            }
        }

        Card(
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEFE9E3)
            )
        ) {
            Column(
                modifier = Modifier.padding(15.dp).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(text = "EMERGING ISSUES", color = cardTitle, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Text(text = meetingData.emergingIssues, fontSize = 13.sp)
            }
        }
    }
}
