// Reusable project assets

package com.zion.elders.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.zion.elders.R
import com.zion.elders.models.AppTab

@Composable
fun BackButton(
    navController: NavHostController
){
    Box(
        modifier = Modifier.size(35.dp).clip(RoundedCornerShape(100.dp))
            .background(nonMonetaryBoxColor).clickable(true){ navController.popBackStack() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(R.drawable.arrow_back),
            contentDescription = "back_img",
            modifier = Modifier.size(20.dp),
            tint = Color.Black
        )
    }
}
@Composable
fun SystemForm(
    modifier: Modifier = Modifier,
    color: Color = systemFormColor,
    content: @Composable ColumnScope.() -> Unit
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
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
fun BottomNavBar(
    currentTab: AppTab,
    onTabSelected: (AppTab) -> Unit
){
    val deepEmerald = Color(0xFF00674F)
    val warmSand = Color(0xFFEFE6DC)
    val espressoText = Color(0xFF3B2618)
    val mutedBrown = Color(0xFF6E472B)

    NavigationBar(
        containerColor = warmSand,
        tonalElevation = 4.dp,
        modifier = Modifier.height(75.dp)
    ) {
        AppTab.entries.forEach { navItem ->
            val isSelected = (currentTab == navItem)

            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(navItem)},
                icon = {
                    Icon(
                        painter = painterResource(if (isSelected) navItem.filledIcon else navItem.outlinedIcon),
                        contentDescription = "nav_item_img")
                },
                label = {
                    Text(
                        text = navItem.label,
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    indicatorColor = deepEmerald,
                    selectedTextColor = deepEmerald,
                    unselectedIconColor = mutedBrown,
                    unselectedTextColor = espressoText.copy(alpha = 0.75f)
                )
            )
        }
    }
}

@Composable
fun CircularFloatingActionButton(
    color: Color,
    onClicked: () -> Unit
){
    Box(modifier = Modifier
        .size(40.dp)
        .clip(RoundedCornerShape(150.dp))
        .background(color)
        .clickable(enabled = true, onClick = onClicked),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(R.drawable.fab_add),
            contentDescription = "FAB",
            modifier = Modifier.size(20.dp),
            tint = Color.White
        )
    }
}

@Composable
fun DatePickerComponent(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
){
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onDismiss()
                }
            ) {
                Text(text = "Done")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}