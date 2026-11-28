package com.zion.elders.components.projectsscreen

import android.icu.text.NumberFormat
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zion.elders.backend.Lib
import com.zion.elders.components.SystemForm
import com.zion.elders.components.cardColor
import com.zion.elders.ui.theme.cardTitle
import com.zion.elders.ui.theme.experimentalBackground
import com.zion.elders.ui.theme.progressBarTrackColor
import com.zion.elders.ui.theme.projectOverviewTrackColor
import com.zion.elders.viewmodels.ProjectsViewModel
import kotlinx.coroutines.launch
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProjectBottomSheet(
    onDismiss: () -> Unit,
    projectsViewModel: ProjectsViewModel = viewModel()
){
    val bottomSheetStateManager by projectsViewModel.projectCreationState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = cardColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(13.dp)
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
                    value = bottomSheetStateManager.projectTitle,
                    onValueChange = { projectsViewModel.onProjectTitleChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
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
                    text = "Project Target (Ksh.)",
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
                TextField(
                    value = bottomSheetStateManager.projectTarget.toString(),
                    onValueChange = { projectsViewModel.onProjectTargetChanged(it.toInt()) },
                    modifier = Modifier
                        .width(150.dp)
                        .height(50.dp),
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
                modifier = Modifier
                    .height(5.dp)
                    .fillMaxWidth()
            )
            Button(
                modifier = Modifier
                    .align(Alignment.End)
                    .fillMaxWidth()
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = experimentalBackground
                ),
                shape = RoundedCornerShape(10.dp),
                enabled = bottomSheetStateManager.projectTitle.isNotEmpty() && bottomSheetStateManager.projectTarget.toString().isNotEmpty(),
                onClick = {
                    Toast.makeText(context, "Saving project: ${bottomSheetStateManager.projectTitle}", Toast.LENGTH_SHORT).show()

                    scope.launch {
                        projectsViewModel.createProject(context, bottomSheetStateManager.projectTitle, bottomSheetStateManager.projectTarget)
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            projectsViewModel.setCreateProjectBottomSheetVisible(false)
                        }
                    }
                }
            ) {
                Text(text = "Create", fontSize = 15.sp, color = Color.White)
            }
        }
    }
}
@Composable
fun ProjectCompletionProgress(
    projectsViewModel: ProjectsViewModel = viewModel()
){
    val unformattedNetContributions = 0.0f
    val unformattedGrossContribution = projectsViewModel.projectScreenUIState.collectAsStateWithLifecycle().value.grossContribution
    val totalContributed = NumberFormat.getInstance(Locale.US).format(unformattedNetContributions)
    val target = NumberFormat.getInstance(Locale.US).format(unformattedGrossContribution)

    // To prevent NaN values when the total contributions is 0:
    var progressSoFar = 0.0f
    if (unformattedNetContributions != 0.0f) {
        progressSoFar = 1.0f/unformattedGrossContribution.toFloat()
    }

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