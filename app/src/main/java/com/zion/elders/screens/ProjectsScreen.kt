package com.zion.elders.screens

import android.icu.text.NumberFormat
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zion.elders.backend.Lib
import com.zion.elders.components.CircularFloatingActionButton
import com.zion.elders.components.SystemForm
import com.zion.elders.components.cardColor
import com.zion.elders.ui.theme.cardTitle
import com.zion.elders.ui.theme.experimentalBackground
import com.zion.elders.ui.theme.moneyBoxColor
import com.zion.elders.ui.theme.progressBarTrackColor
import com.zion.elders.ui.theme.projectOverviewTrackColor
import kotlinx.coroutines.launch
import java.util.Locale

data class ProjectData(
    val projectTitle: String,
    val amountContributed: Float,
    val projectTarget: Float,
){
    val formattedContribution: String get() = NumberFormat.getInstance(Locale.US).format(amountContributed)
    val formattedTarget: String get() = NumberFormat.getInstance(Locale.US).format(projectTarget)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectsScreen(){
    val context = LocalContext.current
    val mockProjectData = listOf(
        ProjectData("Keyboard contribution", 2000.0f, 25000.0f),
        ProjectData("Gas for Mtumishi", 2350.0f, 2350.0f),
        ProjectData("Sound systems", 150.0f, 15000.0f)
    )
    // Modal bottom sheet setup
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                 .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 5.dp)
                 .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Projects", color = cardTitle, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            CircularFloatingActionButton(color = cardTitle) {
                showSheet = true
            }
        }
        ProjectCompletionProgress()

        Spacer(modifier = Modifier
            .height(10.dp)
            .fillMaxWidth())

        Text(text = "Active Projects: (${mockProjectData.size})", color = cardTitle, fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 5.dp))
        mockProjectData.forEach { projectData ->
            ProjectData(
                projectData
            )
        }
        if (showSheet) {
            var projectTitle by remember { mutableStateOf("") }
            var projectTarget by remember { mutableStateOf("") }
            ModalBottomSheet(
                onDismissRequest = { showSheet = false},
                sheetState = sheetState,
                containerColor = cardColor
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(13.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Create New Project", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Color.Black)
                    }

                    SystemForm {
                        Text(
                            text = "Project Title",
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            fontSize = 15.sp
                        )
                        TextField(
                            value = projectTitle,
                            onValueChange = { projectTitle = it },
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            placeholder = {
                                Text("Project Title", fontSize = 14.sp)
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
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(7.dp)
                        )
                        Text(
                            text = "Project Target (Ksh)",
                            fontSize = 15.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold
                        )
                        TextField(
                            value = projectTarget,
                            onValueChange = { projectTarget = it },
                            modifier = Modifier.width(150.dp).height(50.dp),
                            placeholder = {
                                Text("Project Target", fontSize = 14.sp)
                            },
                            textStyle = TextStyle(
                                fontSize = 15.sp,
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
                                disabledContainerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(7.dp)
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(5.dp).fillMaxWidth()
                    )
                    Button(
                        modifier = Modifier.align(Alignment.End).fillMaxWidth().height(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = experimentalBackground
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            Toast.makeText(context, "Saving project: $projectTitle", Toast.LENGTH_SHORT).show()
                            scope.launch {
                                sheetState.hide()
                            }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showSheet = false
                                }
                            }
                        }
                    ) {
                        Text(text = "Create", fontSize = 15.sp, color = Color.White)
                    }
                }
            }
        }
    }
}
@Composable
fun ProjectCompletionProgress(){
    val totalContributed = NumberFormat.getInstance(Locale.US).format(230000.0f)
    val target = NumberFormat.getInstance(Locale.US).format(500000.0f)

    val progressSoFar = 230000.0f/500000.0f

    SystemForm (
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        color = cardTitle
    ) {
        Text(text = "Total Contributions as of ${Lib.getCurrentDate()}",
            fontSize = 17.sp, fontWeight = FontWeight.SemiBold,
            color = Color.White)

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(text = "Ksh.$totalContributed", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "of Ksh.$target", color = Color.LightGray, fontSize = 14.sp, fontWeight = FontWeight.Normal)
        }
        LinearProgressIndicator(
            progress = { progressSoFar },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            color = projectOverviewTrackColor,
            trackColor = progressBarTrackColor,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap
        )
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Overall completion", fontSize = 15.sp, color = Color.LightGray)
            Text(text = "${progressSoFar*100}%", fontSize = 14.sp, color = Color.LightGray)
        }
    }
}

@Composable
fun ProjectData(
    projectData: ProjectData
){
    val projectProgress = projectData.amountContributed / projectData.projectTarget
    val completionProgress = (projectProgress * 100 == 100.0f)
    
    SystemForm(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 7.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = projectData.projectTitle, fontSize = 15.sp, color = cardTitle, fontWeight = FontWeight.SemiBold)
            Box(
                modifier = Modifier.width(65.dp).height(30.dp).clip(RoundedCornerShape(10.dp)).background(
                    if (completionProgress) moneyBoxColor else Color.Red
                ),
                contentAlignment = Alignment.Center
            ){
                Text(text = "${projectProgress*100}%", fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                    color = if (completionProgress) cardTitle else Color.White
                )
            }
        }
        LinearProgressIndicator(
            progress = { projectProgress },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            color = cardTitle,
            trackColor = progressBarTrackColor,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap
        )
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = "Ksh.${projectData.formattedContribution}", color = cardTitle, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                Text(text = " contributed", color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Normal)
            }
            Row {
                Text(text = "Target: ", color = Color.Gray, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                Text(text = "Ksh.${projectData.formattedTarget}", color = Color.Gray, fontSize = 15.sp, fontWeight = FontWeight.Normal)
            }
        }
    }
}