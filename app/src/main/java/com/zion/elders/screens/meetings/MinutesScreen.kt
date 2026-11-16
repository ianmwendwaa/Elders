package com.zion.elders.screens.meetings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.zion.elders.components.BackButton
import com.zion.elders.components.backgroundColor
import com.zion.elders.components.cardColor
import com.zion.elders.components.pillBackgroundColor
import com.zion.elders.components.pillButtonColor
import com.zion.elders.models.EditorMode
import com.zion.elders.screens.minutes.MinutesEditModeScreen
import com.zion.elders.screens.minutes.PreviewMinutesScreen

@Composable
fun MinutesScreen(navController: NavHostController){
    var currentEditorScreen by remember { mutableStateOf(EditorMode.EDIT) }
    var isPreviewModeSelected by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).background(
            backgroundColor
        ),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        // Minutes form header
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(navController)
            Text("Minutes", fontSize = 19.sp, color = Color.Black, fontWeight = FontWeight.Bold)

            // Toggle between Edit and Preview modes
            Box(
                modifier = Modifier.width(100.dp).height(40.dp).clip(RoundedCornerShape(25.dp))
                    .background(pillBackgroundColor).clickable(
                        true
                    ) {
                        isPreviewModeSelected = !isPreviewModeSelected
                    }
            ) {
                Row(modifier = Modifier.matchParentSize()) {
                    EditorMode.entries.forEach { mode ->
                        PillButton(
                            ""
                        )
                    }
                }
            }
        }
        if (!isPreviewModeSelected){
            MinutesEditModeScreen()
        }else{
            PreviewMinutesScreen()
        }
    }
}

@Composable
fun PillButton(
    modeLabel: String
){
    Box(modifier = Modifier.fillMaxHeight().width(50.dp).clip(RoundedCornerShape(100.dp)).background(
        pillButtonColor
    ),
        contentAlignment = Alignment.Center
    ){
        Text(text = modeLabel)
    }
}

@Composable
fun TextFormattingFeature(
    formattingIcon: Int,
    onClick: () -> Unit
){

    Box(
        modifier = Modifier.size(30.dp).clip(RoundedCornerShape(8.dp))
            .background(cardColor).border(1.dp, shape = RoundedCornerShape(8.dp),
                color = Color.Gray).clickable(true){ onClick },
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(formattingIcon),
            contentDescription = "formatting_feature_icon",
            modifier = Modifier.size(15.dp),
            tint = Color.Black
        )
    }
}