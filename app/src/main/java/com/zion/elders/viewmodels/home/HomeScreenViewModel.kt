package com.zion.elders.viewmodels.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zion.core.dto.Member
import com.zion.core.local_database.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class OverviewDataState(
    val totalMembers: Int = 0,
    val netContributions: Int = 0,
    val registeredMembers: List<Member> = emptyList(),
    val errorOccurred: Boolean = false,
    val errorMessage: String ?= null
)

class HomeScreenViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(OverviewDataState())
    val uiState: StateFlow<OverviewDataState> = _uiState.asStateFlow()

    fun getNetContributions(): Int {
        _uiState.update {
            it.copy(netContributions = 1700)
        }
        return _uiState.value.netContributions
    }

    fun getAllMembers(context: Context) {
        viewModelScope.launch {
            val retrievedMembers = AppDatabase.getDatabase(context).memberDAO().getAllMembers()

            _uiState.update {
                it.copy(
                    registeredMembers = retrievedMembers
                )
            }
        }
    }
}