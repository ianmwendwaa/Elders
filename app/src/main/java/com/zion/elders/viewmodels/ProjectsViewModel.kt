package com.zion.elders.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zion.elders.backend.local_database.AppDatabase
import com.zion.elders.backend.local_database.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProjectScreenUIState(
    val amountContributed: Int = 0, // Total amount contributed by date X
    val grossContribution: Int = 0, // Gross total across all project entries (ToDo: Might delete this
    val projectEntries: List<Project> = emptyList(), // List of all registered projects in the app
)

class ProjectsViewModel: ViewModel() {
    private val _projectsUiState = MutableStateFlow(ProjectScreenUIState())
    val projectScreenUIState = _projectsUiState.asStateFlow()

    fun createProject(context: Context, title: String, target: Double){
        viewModelScope.launch {
            val projectsDao = AppDatabase.getDatabase(context).projectsDAO()

            projectsDao.createProject(
                Project(
                    projectTitle = title, projectTarget = target
                )
            )
        }
    }
}