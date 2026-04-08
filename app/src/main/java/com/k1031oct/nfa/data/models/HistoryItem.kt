package com.k1031oct.nfa.data.models

data class HistoryItem(
    val id: String = "",
    val noteId: String = "",
    val noteTitle: String = "",
    val action: String = "", // "Created", "Edited", "Deleted", "Pinned"
    val timestamp: Long = System.currentTimeMillis(),
    val status: String = "Success"
)
