package com.zion.elders.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zion.elders.R
import com.zion.elders.backend.Lib
import com.zion.elders.components.SystemForm
import com.zion.elders.components.cardColor
import com.zion.elders.components.systemFormColor
import com.zion.elders.ui.theme.cardTitle
import com.zion.elders.ui.theme.experimentalBackground
import com.zion.elders.ui.theme.moneyBoxColor
import com.zion.elders.ui.theme.progressBarTrackColor

data class ProjectData(
    val projectTitle: String,
    val amountContributed: Float,
    val projectTarget: Float
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectsScreen(){
    val context = LocalContext.current
    val mockProjectData = listOf(
        ProjectData("Keyboard contribution", 1000.0f, 10000.0f),
        ProjectData("Gas for Mtumishi", 0.0f, 1500.0f),
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
        Row(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 40.dp, bottom = 5.dp)
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

        Text(text = "Active Projects (count)", color = cardTitle, fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
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
                containerColor = systemFormColor
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(13.dp)
                ) {
                    Text(text = "Create New Project", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black,
                        textAlign = TextAlign.Center)
                    Text(text = "Project Title", fontWeight = FontWeight.SemiBold, color = Color.Black, fontSize = 15.sp)
                    TextField(
                        value = projectTitle,
                        onValueChange = { projectTitle = it },
                        modifier = Modifier.fillMaxWidth().height(48.dp),
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
                        text = "Project Target", fontSize = 15.sp, color = Color.Black, fontWeight = FontWeight.SemiBold
                    )
                    TextField(
                        value = projectTarget,
                        onValueChange = { projectTarget = it },
                        modifier = Modifier.width(150.dp).height(48.dp),
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

                    Button(
                        modifier = Modifier.align(Alignment.End),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = cardTitle
                        ),
                        onClick = { Toast.makeText(context, "", Toast.LENGTH_SHORT).show() }
                    ) {
                        Text(text = "Create", fontSize = 15.sp, color = Color.White)
                    }
                }
            }
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
fun ProjectCompletionProgress(){
    val totalContributed = 230000.0f
    val target = 500000.0f

    val progressSoFar = totalContributed/target

    SystemForm (
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
    ) {
        Text(text = "Total Contributions as of ${Lib.getCurrentDate()}",
            fontSize = 17.sp, fontWeight = FontWeight.SemiBold,
            color = Color.Black)

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(text = "Ksh.$totalContributed", color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "of Ksh.$target", color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Normal)
        }
        LinearProgressIndicator(
            progress = { progressSoFar },
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
            Text(text = "Overall completion", fontSize = 15.sp, color = Color.Gray)
            Text(text = "${progressSoFar*100}%", fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun ProjectData(
    projectData: ProjectData
){
    val projectProgress = projectData.amountContributed / projectData.projectTarget
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
                    moneyBoxColor
                ),
                contentAlignment = Alignment.Center
            ){
                Text(text = "${projectProgress*100}%", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
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
                Text(text = "Ksh.${projectData.amountContributed}", color = cardTitle, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                Text(text = " contributed", color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Normal)
            }
            Row {
                Text(text = "Target: ", color = Color.Gray, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                Text(text = "${projectData.projectTarget}", color = Color.Gray, fontSize = 15.sp, fontWeight = FontWeight.Normal)
            }
        }
    }
}