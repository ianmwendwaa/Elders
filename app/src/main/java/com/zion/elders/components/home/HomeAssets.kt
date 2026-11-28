// Reusable UI components in the Home screen

package com.zion.elders.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zion.elders.components.common.SystemForm
import com.zion.elders.components.cardColor
import com.zion.elders.components.nonMonetaryBoxColor
import com.zion.elders.models.MeetingData
import com.zion.elders.screens.meetings.ContributionsListItem
import com.zion.elders.ui.theme.cardIconTint
import com.zion.elders.ui.theme.cardTitle
import com.zion.elders.ui.theme.moneyBoxColor
import com.zion.elders.ui.theme.moneyTextColor


@Composable
fun OverviewDataCard(
    dataLabel: String,
    dataPainterResource: Int,
    dataValue: String,
    dataSecondaryLabel: String = ""
){
    Card(
        modifier = Modifier.width(150.dp).border(1.dp, Color.LightGray, shape = RoundedCornerShape(11.dp)),
        shape = RoundedCornerShape(11.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        )
    ) {
        Column(modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = dataLabel, fontSize = 13.sp, color = Color.Gray)

                Box(modifier = Modifier.size(25.dp).clip(RoundedCornerShape(200.dp))
                    .background(nonMonetaryBoxColor),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        painter = painterResource(dataPainterResource),
                        contentDescription = "overview_data_img",
                        modifier = Modifier.size(12.5.dp),
                        tint = cardIconTint
                    )
                }
            }
            Text(text = dataValue, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

            // This may or may not be included, it's up to me to decide
            Text(text = dataSecondaryLabel, fontSize = 12.sp, color = Color.Gray)

        }
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
            Text(
                text = meetingData.title,
                color = cardTitle,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Box(
                modifier = Modifier.width(70.dp).height(35.dp).clip(RoundedCornerShape(8.dp))
                    .background(moneyBoxColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Ksh.${meetingData.totalContribution}",
                    fontSize = 14.sp, color = moneyTextColor, fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            }
        }

        Text(
            text = meetingData.date,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            fontSize = 14.sp
        )

        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEFE3D7)
            )
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = "Breakdown",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.Gray
                )

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
                Text(
                    text = "EMERGING ISSUES",
                    color = cardTitle,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = meetingData.emergingIssues, fontSize = 13.sp)
            }
        }
    }
}