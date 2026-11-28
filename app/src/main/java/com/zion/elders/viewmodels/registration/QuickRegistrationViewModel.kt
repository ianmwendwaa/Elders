package com.zion.elders.viewmodels.registration

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zion.core.dto.Member
import com.zion.core.local_database.AppDatabase
import com.zion.core.utils.Lib
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MemberRegistrationFormState(
    val fullName: String = "",
    val phoneNumber: String = "",
    val errorMessage: String ?= null,
    val memberDataSubmitted: Boolean = false
)

class QuickRegistrationViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MemberRegistrationFormState())
    val uiState: StateFlow<MemberRegistrationFormState> = _uiState.asStateFlow()

//    val api = API()
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
        val db = AppDatabase.getDatabase(context)
        val transactionsDao = db.memberDAO()

        val (memberName, memberPhoneNumber) = _uiState.value
        val generatedMemberNumber = Lib.generateMemberNumber(memberName, memberPhoneNumber)
        if (memberName.isEmpty() || memberPhoneNumber.isEmpty()){
            _uiState.update { it.copy(errorMessage = "Please provide all inputs", memberDataSubmitted = false) }
        } else {
            _uiState.update { it.copy(errorMessage = null, memberDataSubmitted = true) }
        }

        if (_uiState.value.memberDataSubmitted) {
            Toast.makeText(context, "Generated member number: ${Lib.generateMemberNumber(memberName, memberPhoneNumber)}",
                Toast.LENGTH_SHORT).show()
            viewModelScope.launch {
                transactionsDao.registerMember(
                    Member(
                        memberName = memberName,
                        memberTelephone = memberPhoneNumber,
                        generatedMemberNumber = generatedMemberNumber
                    )
                )
                Log.w("MEMBER_REGISTRATION", "Member $memberName has been registered successfully!")
            }
        }

    }
}