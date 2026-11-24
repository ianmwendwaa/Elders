package com.zion.elders.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zion.elders.backend.local_database.AppDatabase
import com.zion.elders.backend.local_database.Member
import com.zion.elders.transactions.API
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

    val api = API()


    fun getTotalMembers(context: Context) {
        Log.w("MEMBER_SIZE_COUNT", "Attempting to retrieve member count...")
        viewModelScope.launch {
            val transactionsDao = AppDatabase.getDatabase(context).memberDAO()
            val allMembers = transactionsDao.getAllMembers()

            _uiState.update {
                it.copy(
                    totalMembers = allMembers.size
                )
            }
        }
    }
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