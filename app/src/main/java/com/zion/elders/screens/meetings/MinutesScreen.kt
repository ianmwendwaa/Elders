package com.zion.elders.screens.meetings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.zion.elders.components.BackButton
import com.zion.elders.components.SystemForm
import com.zion.elders.components.backgroundColor
import com.zion.elders.components.cardColor
import com.zion.elders.components.pillBackgroundColor
import com.zion.elders.components.pillButtonColor
import com.zion.elders.components.systemFormColor
import com.zion.elders.models.EditorMode
import com.zion.elders.screens.minutes.MinutesEditModeScreen
import com.zion.elders.screens.minutes.PreviewMinutesScreen

@Composable
fun MinutesScreen(navController: NavHostController){
    var currentEditorScreen by remember { mutableStateOf(EditorMode.PREVIEW) }
    val minutesTitle = "Some title"
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).background(
            backgroundColor
        ),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
       SystemForm(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 35.dp)) {
           // Minutes form header
           Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically
           ) {
               BackButton(navController)
               Text("Minutes", fontSize = 21.sp, color = Color.Black, fontWeight = FontWeight.Bold)

               // Toggle between Edit and Preview modes
               Box(
                   modifier = Modifier.width(125.dp).height(40.dp).clip(RoundedCornerShape(25.dp))
                       .background(pillBackgroundColor)
               ) {
                   Row(modifier = Modifier.matchParentSize(),
                       verticalAlignment = Alignment.CenterVertically,
                       horizontalArrangement = Arrangement.spacedBy(3.dp)
                   ) {
                       EditorMode.entries.forEach { mode ->
                           val isSelected = currentEditorScreen == mode

                           PillButton(
                               modeLabel = mode.label,
                               background = if(isSelected) pillButtonColor else Color.Transparent,
                               textStyling = TextStyle(
                                   color = if (isSelected) Color.White else Color.Black,
                                   fontWeight = if(isSelected) FontWeight.Bold else FontWeight.SemiBold
                               ),
                               modifier = Modifier.clickable(true){ currentEditorScreen = mode }
                           )
                       }
                   }
               }
           }
           // Get this value from LogMeetingDataScreen
           TextField(
               value = minutesTitle,
               onValueChange = {},
               enabled = false,
               modifier = Modifier.fillMaxWidth().height(48.dp),
               placeholder = {
                   Text(text = "Meeting title", fontSize = 13.sp, color = Color.Gray)
               },
               textStyle = TextStyle(
                   fontSize = 13.sp,
                   color = Color.Gray,
                   fontWeight = FontWeight.SemiBold
               ),
               colors = TextFieldDefaults.colors(
                   focusedContainerColor = Color.White,
                   unfocusedContainerColor = Color.White,
                   disabledContainerColor = cardColor,
                   focusedIndicatorColor = Color.Transparent,
                   unfocusedIndicatorColor = Color.Transparent
               ),
               shape = RoundedCornerShape(8.dp)
           )
       }

        when(currentEditorScreen){
            EditorMode.EDIT -> {
                MinutesEditModeScreen()
            }
            EditorMode.PREVIEW -> {
                PreviewMinutesScreen()
            }
            else -> EditorMode.UNKNOWN
        }
    }
}

@Composable
fun PillButton(
    modifier: Modifier = Modifier,
    modeLabel: String,
    background: Color,
    textStyling: TextStyle
){
    Box(modifier = modifier.fillMaxHeight().width(60.dp).clip(RoundedCornerShape(100.dp)).background(
        background
    ),
        contentAlignment = Alignment.Center
    ){
        Text(text = modeLabel, fontSize = 13.sp, color = textStyling.color, fontWeight = textStyling.fontWeight)
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

@Composable
fun CustomTextFieldForm(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
){
    Card(modifier = modifier.padding(start = 10.dp, end = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = systemFormColor
        )
    ) {
        Column(modifier = Modifier.padding(8.dp), content = content)
    }
}