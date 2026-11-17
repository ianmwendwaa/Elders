package com.zion.elders.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zion.elders.transactions.API
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

data class OverviewDataState(
    val totalMembers: Int = 0,
    val netContributions: Int = 0
)

class HomeOverviewViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(OverviewDataState())
    val uiState: StateFlow<OverviewDataState> = _uiState.asStateFlow()

    val api = API()
    fun getTotalMembers(): Int {
        viewModelScope.launch {
            try {
                val fetchedMemberCount = api.fetchMembers().size
                _uiState.update {
                    it.copy(totalMembers = fetchedMemberCount)
                }
            } catch (e: Exception) {
                // Fall back to default value 0
                _uiState.update {
                    it.copy(totalMembers = 0)
                }
                println("Error encountered while fetching member count. Reason: ${e.message}")
            }
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