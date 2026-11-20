package com.zion.elders.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MeetingDataEntry(
    val date: String = ""
)
class MeetingEntryViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MeetingDataEntry())
    val uiState: StateFlow<MeetingDataEntry> = _uiState.asStateFlow()

    
}