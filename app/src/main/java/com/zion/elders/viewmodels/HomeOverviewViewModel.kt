package com.zion.elders.viewmodels

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zion.elders.components.systemFormColor
import com.zion.elders.transactions.API
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

data class OverviewDataState(
    val totalMembers: Int = 0,
    val netContributions: Int = 0,
    val errorOccurred: Boolean = false,
    val errorMessage: String ?= null
)

class HomeOverviewViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(OverviewDataState())
    val uiState: StateFlow<OverviewDataState> = _uiState.asStateFlow()

    val api = API()

    init {
        getTotalMembers()
    }
    fun getTotalMembers() {
        Log.w("MEMBER_SIZE_COUNT", "Attempting to retrieve member count...")
        viewModelScope.launch {
            try {
                val fetchedMemberCount = api.fetchMembers().size
                Log.w("MEMBER_SIZE_COUNT", fetchedMemberCount.toString())
                _uiState.update {
                    it.copy(totalMembers = fetchedMemberCount)
                }
            } catch (e: Exception) {
                // Fall back to default value 0
                Log.e("SIZE_COUNT_ERR", "${e.message}")
                _uiState.update {
                    it.copy(
                        errorOccurred = true,
                        errorMessage = "${e.message}",
                        totalMembers = 0
                    )
                }
                Log.w("SIZE_COUNT_ERR", "${_uiState.value.errorOccurred}")
                Log.w("SIZE_COUNT_ERR", "${_uiState.value.errorMessage}")
            }
        }
    }
    fun getNetContributions(): Int {
        _uiState.update {
            it.copy(netContributions = 1700)
        }
        return _uiState.value.netContributions
    }
}