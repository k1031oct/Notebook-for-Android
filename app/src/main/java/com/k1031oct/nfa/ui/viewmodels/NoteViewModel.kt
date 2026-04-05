package com.k1031oct.nfa.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k1031oct.nfa.data.models.Note
import com.k1031oct.nfa.data.repositories.NoteRepository
import com.k1031oct.nfa.ui.states.NoteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState: StateFlow<NoteUiState> = _uiState.asStateFlow()

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.getNotes()
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { notes ->
                    _uiState.update { it.copy(notes = notes, isLoading = false) }
                }
        }
    }

    fun selectNote(note: Note?) {
        _uiState.update { it.copy(selectedNote = note, isEditing = note != null) }
    }

    fun saveNote(note: Note) {
        viewModelScope.launch {
            repository.saveNote(note)
            _uiState.update { it.copy(isEditing = false, selectedNote = null) }
        }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            repository.deleteNote(noteId)
        }
    }

    fun changeRefill(refillType: String) {
        _uiState.update { it.copy(activeRefill = refillType) }
    }

    fun showSection(section: String) {
        _uiState.update { it.copy(activeSection = section) }
        Log.d("ORBIT", "Section switched to: $section")
    }

    private var calculatorInput = ""
    private var calculatorLastValue = 0.0
    private var calculatorPendingOp = ""

    fun onCalculatorAction(action: String) {
        Log.d("ORBIT", "Calculator Action: $action")
        when (action) {
            "C" -> {
                calculatorInput = ""
                calculatorLastValue = 0.0
                calculatorPendingOp = ""
                _uiState.update { it.copy(calculatorDisplay = "0") }
            }
            "=" -> {
                if (calculatorInput.isNotEmpty() && calculatorPendingOp.isNotEmpty()) {
                    val current = calculatorInput.toDoubleOrNull() ?: 0.0
                    val result = calculate(calculatorLastValue, current, calculatorPendingOp)
                    _uiState.update { it.copy(calculatorDisplay = result.toString()) }
                    calculatorLastValue = result
                    calculatorInput = ""
                    calculatorPendingOp = ""
                }
            }
            "+", "-", "*", "/" -> {
                if (calculatorInput.isNotEmpty()) {
                    calculatorLastValue = calculatorInput.toDoubleOrNull() ?: 0.0
                    calculatorPendingOp = action
                    calculatorInput = ""
                } else if (calculatorPendingOp.isEmpty()) {
                    // 前回計算結果から継続
                    calculatorLastValue = _uiState.value.calculatorDisplay.toDoubleOrNull() ?: 0.0
                    calculatorPendingOp = action
                }
            }
            else -> { // Numbers and dot
                calculatorInput += action
                _uiState.update { it.copy(calculatorDisplay = calculatorInput) }
            }
        }
    }

    private fun calculate(val1: Double, val2: Double, op: String): Double {
        return when (op) {
            "+" -> val1 + val2
            "-" -> val1 - val2
            "*" -> val1 * val2
            "/" -> if (val2 != 0.0) val1 / val2 else Double.NaN
            else -> val2
        }
    }
}
