package com.zion.elders.screens.minutes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.zion.elders.components.BackButton
import com.zion.elders.components.SystemForm
import com.zion.elders.components.backgroundColor
import com.zion.elders.components.cardColor
import com.zion.elders.components.minutes.PillButton
import com.zion.elders.components.pillBackgroundColor
import com.zion.elders.models.EditorMode
import com.zion.elders.viewmodels.minuteseditor.MinutesModeManagerViewModel

@Composable
fun MinutesScreen(navController: NavHostController, viewModelFactory: MinutesModeManagerViewModel = viewModel()){
    val minutesEditor by viewModelFactory.editorMode.collectAsStateWithLifecycle()
    val currentEditorScreen = minutesEditor.mode
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
                       EditorMode.entries.forEach { _ ->
                           PillButton(
                               selectedMode = currentEditorScreen,
                               onActiveModeChanged = { viewModelFactory.onSetActiveEditorMode(it) }
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
                   Text(text = "Meeting title", fontSize = 14.sp, color = Color.Gray)
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
                MinutesEditModeScreen(
                    minutes = minutesEditor.minutesLogged,
                    managerViewModelState = minutesEditor,
                    onMinutesUpdated = { viewModelFactory.onMinutesUpdated(it) }
                )
            }
            EditorMode.PREVIEW -> {
                PreviewMinutesScreen(
                    minutes = minutesEditor.minutesLogged,
                    managerViewModelState = minutesEditor
                )
            }
        }
    }
}