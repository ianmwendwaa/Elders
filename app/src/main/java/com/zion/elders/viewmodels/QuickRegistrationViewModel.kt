package com.zion.elders.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.lifecycle.ViewModel
import com.zion.elders.backend.Lib
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MemberRegistrationFormState(
    val fullName: String = "",
    val phoneNumber: String = "",
    val errorMessage: String ?= null,
    val memberDataSubmitted: Boolean = false
)

class QuickRegistrationViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MemberRegistrationFormState())
    val uiState: StateFlow<MemberRegistrationFormState> = _uiState.asStateFlow()

    fun onFullNameChanged(newText: String){
        _uiState.update { currentState ->
            currentState.copy(
                fullName = newText,
                errorMessage = null
            )
        }
    }

    fun onPhoneNumberChanged(newText: String, keyboardController: SoftwareKeyboardController?, focusManager: FocusManager){
        _uiState.update { currentState ->
            currentState.copy(
                phoneNumber = newText,
                errorMessage = null
            )
        }
        if (newText.length == 10) {
            keyboardController?.hide()
            focusManager.clearFocus()
        }
    }

    fun onRegisterMember(context: Context){
        val (memberName, memberPhoneNumber) = _uiState.value

        if (memberName.isEmpty() || memberPhoneNumber.isEmpty()){
            _uiState.update { it.copy(errorMessage = "Please provide all inputs", memberDataSubmitted = false) }
        } else {
            _uiState.update { it.copy(errorMessage = null, memberDataSubmitted = true) }
        }

        if (_uiState.value.memberDataSubmitted) {
            Toast.makeText(context, "Generated member number: ${Lib.generateMemberNumber(memberName, memberPhoneNumber)}",
                Toast.LENGTH_SHORT).show()
        }
    }
}