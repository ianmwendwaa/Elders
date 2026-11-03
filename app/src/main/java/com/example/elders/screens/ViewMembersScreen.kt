package com.example.elders.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elders.components.TableComponent
import com.example.elders.models.TableModel
import com.example.elders.styles.HeadingText
import org.w3c.dom.Text

data class Members(val name: String, val phoneNumber: String, val memberNumber: String): TableModel
val members = listOf<Members>(
    Members("Ian Mwendwa", "0791930433", "ELDR-IANM-0433"),
    Members("Ian Mwendwa", "0791930433", "ELDR-IANM-0433"),
    Members("Ian Mwendwa", "0791930433", "ELDR-IANM-0433"),
    Members("Ian Mwendwa", "0791930433", "ELDR-IANM-0433"),
    Members("Ian Mwendwa", "0791930433", "ELDR-IANM-0433"),
    Members("Christiano Mwendwa", "0791930433", "ELDR-IANM-0433")
)
@Composable
fun ViewMembersScreen(navController: NavHostController){
    val rows = members.chunked(1)
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(start = 20.dp, top = 35.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        item {
            HeadingText(text = "Members")
        }

        items(rows) {
            TableComponent(
                listOf("Member name", "Phone #", "Member #"),
                members,
                rowCellFactory = {
                    listOf(it.name, it.phoneNumber, it.memberNumber)
                }
            )
        }
//        items(rows) { rowItem ->
//            Row(modifier = Modifier.fillMaxWidth()
//                .border(0.5.dp, color = Color.Gray)
//            ) {
//                rowItem.forEach { memberData ->
//                    Text(text = memberData.name, modifier = Modifier.weight(1f).padding(5.dp))
//                    Text(text = memberData.phoneNumber, modifier = Modifier.weight(1f).padding(5.dp))
//                    Text(text = memberData.memberNumber, modifier = Modifier.weight(1f).padding(5.dp))
//                }
//            }
//        }
    }
}