package com.zion.elders.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zion.elders.components.DataInputField

private data class ContributionData(
    val contributorsName: String,
    val contributionAmt: Int
)
@Composable
fun LogMeetingDataScreen(){
    var meetingTitle by remember { mutableStateOf("") }
    var meetingDate by remember { mutableStateOf("") }
    var meetingAOB by remember { mutableStateOf("") }
    var activeMember by remember { mutableStateOf("") }
    var contributionSliderValue by remember { mutableFloatStateOf(0.5f) }

    val contributionsList = remember { mutableStateListOf<ContributionData>() }
    val totalContributions by remember {
        derivedStateOf {
            contributionsList.sumOf { it.contributionAmt }
        }
    }
    Column(modifier = Modifier.fillMaxSize()
        .background(Color.White).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Card(modifier = Modifier.padding(16.dp).fillMaxWidth()
            .border(0.1.dp, shape = RoundedCornerShape(10.dp), color = Color.LightGray)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Log Meeting Data", fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(10.dp))

                // Input fields for meeting details (Title, date & AOB)
                DataInputField(
                    modifier = Modifier.height(50.dp).fillMaxWidth(),
                    dataInputLabel = "MEETING TITLE",
                    value = meetingTitle,
                    onValueChange = { meetingTitle = it },
                    acceptStringData = true,
                    isSingleLine = true
                )

                DataInputField(
                    modifier = Modifier.height(50.dp).fillMaxWidth(),
                    dataInputLabel = "MEETING DATE",
                    value = meetingDate,
                    onValueChange = { meetingDate = it },
                    isSingleLine = true,
                    acceptStringData = true
                )

                DataInputField(
                    modifier = Modifier.height(100.dp).fillMaxWidth(),
                    dataInputLabel = "AOB",
                    value = meetingAOB,
                    onValueChange = { meetingAOB = it },
                    isSingleLine = false,
                    acceptStringData = true
                )
            }
        }

        Card(
            modifier = Modifier.padding(16.dp)
                .border(0.1.dp, shape = RoundedCornerShape(10.dp), color = Color.Gray)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Append Contributions", fontWeight = FontWeight.Bold)
                DataInputField(
                    modifier = Modifier.height(50.dp).fillMaxWidth(),
                    value = activeMember,
                    onValueChange = { activeMember = it },
                    dataInputLabel = "SELECT ACTIVE MEMBER"
                )

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Amount contributed", color = Color.Gray)
                    Text(text = "Ksh.${contributionSliderValue.toInt()}")
                }

                Slider(
                    value = contributionSliderValue,
                    onValueChange = { contributionSliderValue = it },
                    steps = 3,
                    valueRange = 0f..200f
                )

                Button(onClick = { appendData(contributionsList, activeMember, contributionSliderValue.toInt()) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(7.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF50C878))
                ) {
                    Text("Push Contribution")
                }
            }
        }
        // Contributions panel list
        Card(modifier = Modifier.padding(16.dp)
            .border(0.1.dp, shape = RoundedCornerShape(10.dp), color = Color.Gray)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Appended Contributions")
                    Text(text = "Total: Ksh.$totalContributions")
                }

                if (contributionsList.isEmpty()){
                    Text(text = "No contributions pushed yet.", fontSize = 14.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.SemiBold)
                } else {
                    contributionsList.forEach { contribution ->
                        ContributionsListItem(contribution.contributorsName, contribution.contributionAmt)
                    }
                }

                Button(onClick = {}, modifier = Modifier
                    .fillMaxWidth(),
                    shape = RoundedCornerShape(13.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008080))
                ) {
                    Text(text = "Save meeting data")
                }

            }
        }
    }
}

@Composable
fun ContributionsListItem(
    contributersName: String,
    contributionAmt: Int
){
    // Yes, I know I named it contributee
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(contributersName, fontWeight = FontWeight.SemiBold, color = Color.Gray)
        Text(text = "Ksh.$contributionAmt", fontWeight = FontWeight.SemiBold, color = Color(0xFF50C878))
    }
}

@Preview
@Composable
fun Previewer(){
    LogMeetingDataScreen()
}

private fun appendData(listr: MutableList<ContributionData>, name: String, amt: Int){
    if (name.isEmpty()) return
    listr.add(ContributionData(name, amt))
}