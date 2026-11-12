package com.example.elders.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.elders.R
import com.example.elders.components.HomeActionsCard
import com.example.elders.components.TableComponent
import com.example.elders.models.HomeActions
import com.example.elders.models.MeetingData
import com.example.elders.navigation.ROUTE_ADD_NEW_MEMBER
import com.example.elders.navigation.ROUTE_CREATE_MEETING_ENTRY
import com.example.elders.navigation.ROUTE_VIEW_MEMBERS
import com.example.elders.styles.HeadingText
import com.example.elders.styles.SecondaryHeadingText

private val adminActions = listOf<HomeActions>(
    HomeActions(R.drawable.ic_launcher_foreground, "Add new member", ROUTE_ADD_NEW_MEMBER),
    HomeActions(R.drawable.ic_launcher_foreground, "View members", ROUTE_VIEW_MEMBERS),
    HomeActions(R.drawable.ic_launcher_foreground, "Record meeting", ROUTE_CREATE_MEETING_ENTRY),
)

private val meetingData = listOf<MeetingData>(
    MeetingData("11/10/2026", 250.00),
    MeetingData("18/10/2026", 300.00),
    MeetingData("25/11/2026", 150.00),
    MeetingData("01/11/2026", 250.00),
)
@Composable
fun HomeScreen(navController: NavHostController){
    val backgroundColor = Color(0xFFF8F9FA)
    Column(
        modifier = Modifier.fillMaxSize().background(backgroundColor).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Column (modifier = Modifier
            .padding(start = 20.dp, top = 45.dp, end = 20.dp, bottom = 20.dp)
        ) {
            HeadingText(text = "Welcome!")

            Spacer(modifier = Modifier.height(15.dp))

            // Actions segment
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Actions", fontWeight = FontWeight.SemiBold, fontSize = 18.sp, color = Color(0xFF2C3E50))
                adminActions.chunked(2).forEach { actionPairs ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Left Action card
                        Box(modifier = Modifier.weight(1f)){
                            val leftActionCard = actionPairs[0]

                            HomeActionsCard(
                                cardActionImage = leftActionCard.actionImage,
                                cardActionLabel = leftActionCard.actionName
                            ) {
                                navController.navigate(leftActionCard.actionRoute)
                            }
                        }
                        // Right action card
                        Box(modifier = Modifier.weight(1f)){
                            if (actionPairs.size > 1){
                                HomeActionsCard(
                                    cardActionImage = actionPairs[1].actionImage,
                                    cardActionLabel = actionPairs[1].actionName
                                ) {
                                    navController.navigate(actionPairs[1].actionRoute)
                                }
                            }else {
                                Spacer(modifier = Modifier.fillMaxWidth())
                            }
                        }
                    }
                }
            }

            // Meeting History
            Spacer(modifier = Modifier.height(15.dp))
            SecondaryHeadingText(text = "Meeting History")

            TableComponent(
                listOf("Date", "Total Contribution"),
                meetingData
            ) {
                listOf(it.date, it.contribution.toString())
            }
        }
    }
}