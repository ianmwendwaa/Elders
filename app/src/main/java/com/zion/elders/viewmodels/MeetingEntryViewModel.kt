package com.zion.elders.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MeetingDataEntry(
    val showDatePickerDialog: Boolean = false, // Visibility state
    val datePicked: Long ?= null,
    val minutesTitle: String = "",
    val sliderValue: Float = 0.5f
)

class MeetingEntryViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MeetingDataEntry())
    val uiState: StateFlow<MeetingDataEntry> = _uiState.asStateFlow()

    fun onMinutesTitleChanged(newTitle: String) {
        _uiState.update {
            it.copy(
                minutesTitle = newTitle
            )
        }
    }
    
}