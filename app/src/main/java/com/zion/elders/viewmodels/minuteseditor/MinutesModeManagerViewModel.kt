package com.zion.elders.viewmodels.minuteseditor

import androidx.lifecycle.ViewModel
import com.zion.elders.backend.Lib
import com.zion.elders.models.EditorMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class EditorData(
    val mode: EditorMode = EditorMode.EDIT, // Default mode
    val minutesLogged: String = "",
    val dateOfLogging: String = Lib.getCurrentDate()
)
class MinutesModeManagerViewModel: ViewModel() {
    private val _editorMode = MutableStateFlow(EditorData())
    val editorMode = _editorMode.asStateFlow()

    fun onSetActiveEditorMode(mode: EditorMode) {
        _editorMode.update {
            it.copy(
                mode = mode
            )
        }
    }

    fun onMinutesUpdated(newMinutes: String) {
        _editorMode.update {
            it.copy(
                minutesLogged = newMinutes
            )
        }
    }
}