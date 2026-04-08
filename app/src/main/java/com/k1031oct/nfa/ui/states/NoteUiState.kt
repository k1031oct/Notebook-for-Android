package com.k1031oct.nfa.ui.states

import com.k1031oct.nfa.data.models.Note
import com.k1031oct.nfa.data.models.Folder
import com.k1031oct.nfa.data.models.HistoryItem
import com.google.firebase.auth.FirebaseUser

enum class ThemeMode {
    SYSTEM, LIGHT, DARK
}

data class NoteUiState(
    val notes: List<Note> = emptyList(),
    val folders: List<Folder> = emptyList(),
    val history: List<HistoryItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedNote: Note? = null,
    val isEditing: Boolean = false,
    val activeRefill: String = "Standard",
    val activeSection: String = "memo", // memo, todo, calendar, map, tools
    val activeFolderId: String? = null,
    val calculatorDisplay: String = "0",
    val selectedDate: String = "",
    val currentMonth: String = "",
    val searchQuery: String = "",
    val currentUser: FirebaseUser? = null,
    val calendarViewType: String = "Month", // Month, Week, Day
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val holidayCountry: String = "JP",
    val isGoogleCalendarConnected: Boolean = false,
    val isGoogleDocsConnected: Boolean = false
)
