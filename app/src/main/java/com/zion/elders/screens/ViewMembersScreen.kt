package com.zion.elders.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zion.core.dto.TableModel
import com.zion.elders.components.TableComponent
import com.zion.elders.styles.HeadingText
data class Members(val name: String, val phoneNumber: String, val number: String): TableModel

private val members = listOf(
    Members("Ian Mwendwa", "0791930433", "ELDR-IANM-0433"),
    Members("Ian Mwendwa", "0791930433", "ELDR-IANM-0433"),
    Members("Ian Mwendwa", "0791930433", "ELDR-IANM-0433"),
    Members("Ian Mwendwa", "0791930433", "ELDR-IANM-0433"),
    Members("Ian Mwendwa", "0791930433", "ELDR-IANM-0433")
)
@Composable
fun ViewMembersScreen(){
//    val members = viewModel.members.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(start = 20.dp, top = 35.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        item {
            HeadingText(text = "Members")
        }

        item {
            TableComponent(
                listOf("Member name", "Phone #", "Member #"),
                members,
                rowCellFactory = {
                    listOf(it.name, it.phoneNumber, it.number)
                }
            )
        }
    }
}