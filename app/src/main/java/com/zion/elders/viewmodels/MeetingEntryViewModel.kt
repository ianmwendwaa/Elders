package com.zion.elders.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ContributionData(
    val contributorsName: String,
    val contributionAmt: Int
)
data class MeetingDataEntry(
    val showDatePickerDialog: Boolean = false, // Visibility state of the date picker
    val datePicked: Long ?= null, // Date picked variable from the date picker dialog
    val minutesTitle: String = "", // Title of the minutes
    val sliderValue: Float = 0.5f, // Contribution's slider value (initial value is 50)
    val contributionsList: List<ContributionData> = emptyList(), // To populate the list of contributions
    val totalContributions: Int = 0, // Summation of the day's contribution
    val selectedMember: String = "Select Member", // Default value of the member selected
    val isDropDownMenuExpanded: Boolean = false
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

    fun onDateSelectionChanged(selectedDate: Long?){
        _uiState.update{
            it.copy(
                datePicked = selectedDate
            )
        }
    }
    fun onChangeDateDialogVisibility(isVisible: Boolean){
        if (_uiState.value.showDatePickerDialog != isVisible){
            _uiState.update {
                it.copy(
                    showDatePickerDialog = isVisible
                )
            }
        }
    }

    fun setSelectedMember(member: String) {
        _uiState.update {
            it.copy(
                selectedMember = member
            )
        }
    }
    fun onChangeDropDownMenuVisibility(isVisible: Boolean) {
        if (_uiState.value.showDatePickerDialog != isVisible) {
            _uiState.update {
                it.copy(
                    isDropDownMenuExpanded = isVisible
                )
            }
        }
    }

    fun onSliderValueChanged(newSliderValue: Float) {
        _uiState.update {
            it.copy(
                sliderValue = newSliderValue
            )
        }
    }
}