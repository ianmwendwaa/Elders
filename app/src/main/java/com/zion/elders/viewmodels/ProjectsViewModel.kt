package com.zion.elders.viewmodels

import android.content.Context
import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.test.core.app.ApplicationProvider
import com.zion.elders.backend.local_database.AppDatabase
import com.zion.elders.backend.local_database.Project
import com.zion.elders.models.projects_data.ProjectData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProjectScreenUIState(
    val amountContributed: Int = 0, // Total amount contributed by date X
    val grossContribution: Int = 0, // Gross total across all project entries
    val projectEntries: List<Project> = emptyList(), // List of all registered projects in the app
    val showCreateProjectBottomSheet: Boolean = false
)

data class ProjectCreationState(
    val projectTitle: String = "",
    val projectTarget: Int = 0
)

class ProjectsViewModel: ViewModel() {
    private val _projectsUiState = MutableStateFlow(ProjectScreenUIState())
    val projectScreenUIState: StateFlow<ProjectScreenUIState> = _projectsUiState.asStateFlow()

    private val _projectCreationState = MutableStateFlow(ProjectCreationState())
    val projectCreationState = _projectCreationState.asStateFlow()



    fun onGetContext(context: Context = ApplicationProvider.getApplicationContext()): Context{
        return context
    }

    fun getActiveProjects(context: Context) {
        val mockProjectData = listOf(
            ProjectData("Keyboard contribution", 2000.0, 25000.0),
            ProjectData("Gas for Mtumishi", 2350.0, 2350.0),
            ProjectData("Sound systems", 150.0, 15000.0)
        )
        val pDao = AppDatabase.getDatabase(context).projectsDAO()
        viewModelScope.launch {
            val registeredProjects = pDao.getAllProjects()
            _projectsUiState.update {
                it.copy(
                    projectEntries = registeredProjects
                )
            }
        }
    }

    fun calculateGrossContributions(context: Context){
        viewModelScope.launch {
            val pDao = AppDatabase.getDatabase(context).projectsDAO()
            val registeredProjects = pDao.getAllProjects()
            var sum = 0
            registeredProjects.forEach { project ->
                sum += project.projectTarget.toInt()

                _projectsUiState.update {
                    it.copy(
                        grossContribution = sum
                    )
                }
            }

        }
    }
    // Project creation functions
    fun onProjectTitleChanged(newProjectTitle: String) {
        _projectCreationState.update {
            it.copy(
                projectTitle = newProjectTitle
            )
        }
    }
    fun onProjectTargetChanged(newProjectTarget: Int) {
        _projectCreationState.update {
            it.copy(
                projectTarget = newProjectTarget
            )
        }
    }

    fun setCreateProjectBottomSheetVisible(visible: Boolean) {
        if (_projectsUiState.value.showCreateProjectBottomSheet != visible) {
            _projectsUiState.update {
                it.copy(
                    showCreateProjectBottomSheet = visible
                )
            }
        }
    }
    fun createProject(context: Context, title: String, target: Int){
        viewModelScope.launch {
            val projectsDao = AppDatabase.getDatabase(context).projectsDAO()

            projectsDao.createProject(
                Project(
                    projectTitle = title, projectTarget = target.toDouble()
                )
            )
        }
    }
}