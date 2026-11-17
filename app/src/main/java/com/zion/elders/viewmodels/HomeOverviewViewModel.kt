package com.zion.elders.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class OverviewDataState(
    val totalMembers: Int = 0,
    val netContributions: Int = 0
)

class HomeOverviewViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(OverviewDataState())
    val uiState: StateFlow<OverviewDataState> = _uiState.asStateFlow()

    fun getTotalMembers(): Int {
        _uiState.update {
            it.copy(totalMembers = 5)
        }
        return _uiState.value.totalMembers
    }
    fun getNetContributions(): Int {
        _uiState.update {
            it.copy(netContributions = 1700)
        }
        return _uiState.value.netContributions
    }
}