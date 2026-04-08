package com.k1031oct.nfa.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k1031oct.nfa.data.models.Note
import com.k1031oct.nfa.data.models.Folder
import com.k1031oct.nfa.data.models.HistoryItem
import com.k1031oct.nfa.data.repositories.NoteRepository
import com.k1031oct.nfa.ui.states.NoteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.k1031oct.nfa.data.repositories.AuthRepository
import android.util.Log
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState: StateFlow<NoteUiState> = _uiState.asStateFlow()

    init {
        val now = LocalDate.now()
        _uiState.update { it.copy(
            selectedDate = now.toString(),
            currentMonth = YearMonth.now().toString(),
            currentUser = authRepository.currentUser
        ) }
        loadNotes()
        loadFolders()
        loadHistory()
    }

    fun loadNotes() {
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

    fun loadFolders() {
        viewModelScope.launch {
            repository.getFolders().collect { folders ->
                _uiState.update { it.copy(folders = folders) }
            }
        }
    }

    fun loadHistory() {
        viewModelScope.launch {
            repository.getHistory().collect { history ->
                _uiState.update { it.copy(history = history) }
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

    fun deleteNote(noteId: String, title: String) {
        viewModelScope.launch {
            repository.deleteNote(noteId, title)
        }
    }

    fun toggleFavorite(note: Note) {
        viewModelScope.launch {
            repository.saveNote(note.copy(isFavorite = !note.isFavorite))
        }
    }

    fun moveToFolder(note: Note, folderId: String?) {
        viewModelScope.launch {
            repository.saveNote(note.copy(folderId = folderId))
        }
    }

    fun createFolder(name: String) {
        viewModelScope.launch {
            repository.saveFolder(Folder(name = name))
        }
    }

    fun toggleFolderLock(folder: Folder) {
        viewModelScope.launch {
            repository.saveFolder(folder.copy(isLocked = !folder.isLocked))
        }
    }

    fun deleteFolder(folderId: String) {
        viewModelScope.launch {
            repository.deleteFolder(folderId)
            if (_uiState.value.activeFolderId == folderId) {
                _uiState.update { it.copy(activeFolderId = null) }
            }
        }
    }

    fun selectFolder(folderId: String?) {
        _uiState.update { it.copy(activeFolderId = folderId) }
    }

    fun changeRefill(refillType: String) {
        _uiState.update { it.copy(activeRefill = refillType) }
        Log.d("ORBIT", "Refill changed to: $refillType")
    }

    fun showSection(section: String) {
        _uiState.update { it.copy(activeSection = section) }
        Log.d("ORBIT", "Section switched to: $section")
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun selectDate(date: String) {
        _uiState.update { it.copy(selectedDate = date) }
    }

    fun nextMonth() {
        val current = YearMonth.parse(_uiState.value.currentMonth)
        val next = current.plusMonths(1)
        _uiState.update { it.copy(currentMonth = next.toString()) }
    }

    fun previousMonth() {
        val current = YearMonth.parse(_uiState.value.currentMonth)
        val prev = current.minusMonths(1)
        _uiState.update { it.copy(currentMonth = prev.toString()) }
    }

    fun changeCalendarViewType(type: String) {
        _uiState.update { it.copy(calendarViewType = type) }
    }

    fun setThemeMode(mode: com.k1031oct.nfa.ui.states.ThemeMode) {
        _uiState.update { it.copy(themeMode = mode) }
    }

    fun setHolidayCountry(country: String) {
        _uiState.update { it.copy(holidayCountry = country) }
    }

    fun toggleGoogleCalendar(enabled: Boolean) {
        _uiState.update { it.copy(isGoogleCalendarConnected = enabled) }
    }

    fun toggleGoogleDocs(enabled: Boolean) {
        _uiState.update { it.copy(isGoogleDocsConnected = enabled) }
    }

    fun quickAddNote(content: String) {
        if (content.isBlank()) return
        viewModelScope.launch {
            val note = Note(
                title = "Quick Note ${LocalDate.now()}",
                content = content,
                date = LocalDate.now().toString()
            )
            repository.saveNote(note)
        }
    }

    fun toggleNoteCompletion(note: Note) {
        // Optimistic UI Update: Firestoreの結果を待たずにUIを変更
        val updatedNotes = _uiState.value.notes.map {
            if (it.id == note.id) it.copy(isCompleted = !it.isCompleted) else it
        }
        _uiState.update { it.copy(notes = updatedNotes) }

        viewModelScope.launch {
            try {
                repository.saveNote(note.copy(isCompleted = !note.isCompleted))
            } catch (e: Exception) {
                // 失敗時は再読み込みしてロールバック
                Log.e("ORBIT", "Failed to sync status to Firestore: ${e.message}", e)
                loadNotes()
            }
        }
    }

    fun signInWithGoogle(idToken: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                authRepository.signInWithGoogle(idToken)
                _uiState.update { it.copy(currentUser = authRepository.currentUser) }
                onSuccess()
                loadNotes()
            } catch (e: Exception) {
                Log.e("ORBIT", "Google Sign-In failed", e)
            }
        }
    }

    fun signOut() {
        authRepository.signOut()
        _uiState.update { it.copy(currentUser = null, notes = emptyList()) }
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

    fun saveCalculatorResultAsNote() {
        val result = _uiState.value.calculatorDisplay
        if (result != "0" && result != "NaN") {
            viewModelScope.launch {
                val newNote = Note(
                    title = "Calculation Result",
                    content = "Result: $result",
                    tags = listOf("Calculator")
                )
                repository.saveNote(newNote)
            }
        }
    }
}
