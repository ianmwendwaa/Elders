package com.zion.elders.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zion.elders.components.common.CircularFloatingActionButton
import com.zion.elders.components.projectsscreen.ProjectCardData
import com.zion.elders.components.projectsscreen.CreateProjectBottomSheet
import com.zion.elders.components.projectsscreen.ProjectCompletionProgress
import com.zion.elders.ui.theme.cardTitle
import com.zion.elders.viewmodels.projects.ProjectsViewModel

@Composable
fun ProjectsScreen(
    projectsViewModel: ProjectsViewModel = viewModel()
){
    val projectScreenUIState by projectsViewModel.projectScreenUIState.collectAsStateWithLifecycle()
    val activeProjects = projectScreenUIState.projectEntries
    val showSheet = projectScreenUIState.showCreateProjectBottomSheet
    val ctx = LocalContext.current

    LaunchedEffect(Unit) {
        projectsViewModel.getActiveProjects(ctx)
        projectsViewModel.calculateGrossContributions(ctx)
    }

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
                projectsViewModel.setCreateProjectBottomSheetVisible(true)
            }
        }
        ProjectCompletionProgress()

        Spacer(modifier = Modifier
            .height(10.dp)
            .fillMaxWidth())

        Text(text = "Active Projects: (${activeProjects.size})", color = cardTitle, fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 5.dp))
        activeProjects.forEach { projectData ->
            ProjectCardData(projectData)
        }
        if (showSheet) {
            CreateProjectBottomSheet(
                onDismiss = { projectsViewModel.setCreateProjectBottomSheetVisible(false) }
            )
        }
    }
}