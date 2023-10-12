package com.khalbro.colornote.presentation.editnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalbro.colornote.domain.models.InfoNote
import com.khalbro.colornote.domain.usecase.GetNoteByIdUseCase
import com.khalbro.colornote.domain.usecase.SaveNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed interface InfoNoteState {
    object Loading : InfoNoteState
    data class Success(val infoNote: InfoNote) : InfoNoteState
}

class EditNoteViewModel(
    savedStateHandle: SavedStateHandle,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase
) : ViewModel() {

    private val argsId = EditNoteFragmentArgs.fromSavedStateHandle(savedStateHandle)
    private val id = argsId.noteId

    private val _state: MutableLiveData<InfoNoteState> = MutableLiveData<InfoNoteState>()
    var state: LiveData<InfoNoteState> = _state

    private val _navigateBackEvent: MutableLiveData<Unit> = MutableLiveData<Unit>()
    var navigateBackEvent: LiveData<Unit> = _navigateBackEvent

    init {
        getNoteById(id)
    }

    private fun insertNote(note: InfoNote) = viewModelScope.launch(Dispatchers.IO) {
        saveNoteUseCase.invoke(note)
    }

    private fun getNoteById(id: Long) = viewModelScope.launch(Dispatchers.IO) {
//        _state.postValue(InfoNoteState.Loading)
        withContext(Dispatchers.Main) { _state.value = InfoNoteState.Loading }
        val noteById = getNoteByIdUseCase.invoke(id)
        withContext(Dispatchers.Main) {

            noteById?.let {
                _state.value = InfoNoteState.Success(it)
            } ?: run {
                _state.value = InfoNoteState.Success(
                    InfoNote(
                        text = "",
                        title = "",
                        id = null,
                        date = InfoNote.getDateNote()
                    )
                )
            }
        }
    }

    fun onTextChanged(text: String) {
        val currentState = state.value
        if (currentState !is InfoNoteState.Success) return
        _state.value = currentState.copy(infoNote = currentState.infoNote.copy(text = text))
    }

    fun onTitleChanged(text: String) {
        val currentState = state.value
        if (currentState !is InfoNoteState.Success) return
        _state.value = currentState.copy(infoNote = currentState.infoNote.copy(title = text))
    }

    fun onSaveClick() {
        val currentState = state.value
        if (currentState !is InfoNoteState.Success) return
        insertNote(currentState.infoNote.copy(date = InfoNote.getDateNote()))
        _navigateBackEvent.value = Unit
    }

    fun onColorChanged(color: String) {
        val currentState = state.value
        if (currentState !is InfoNoteState.Success) return
        _state.value = currentState.copy(infoNote = currentState.infoNote.copy(color = color))
    }
}

