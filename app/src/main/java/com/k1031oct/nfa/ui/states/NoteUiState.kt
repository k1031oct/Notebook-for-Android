package com.k1031oct.nfa.ui.states

import com.k1031oct.nfa.data.models.Note

data class NoteUiState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedNote: Note? = null,
    val isEditing: Boolean = false,
    val activeRefill: String = "standard",
    val activeSection: String = "memo", // "memo", "calculator", "calendar", etc.
    val calculatorDisplay: String = "0"
)
