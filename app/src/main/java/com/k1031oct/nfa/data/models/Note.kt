package com.k1031oct.nfa.data.models

data class Note(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val refillType: String = "standard", // "grid", "lines", "todo", etc.
    val timestamp: Long = System.currentTimeMillis(),
    val date: String? = null,
    val time: String? = null,
    val isFavorite: Boolean = false,
    val folderId: String? = null,
    val isCompleted: Boolean = false,
    val imageUrl: String? = null,
    val tags: List<String> = emptyList(),
    val subtasks: List<SubTask> = emptyList()
)
