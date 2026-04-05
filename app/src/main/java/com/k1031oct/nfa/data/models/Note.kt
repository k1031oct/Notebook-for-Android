package com.k1031oct.nfa.data.models

data class Note(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val refillType: String = "standard", // "grid", "lines", "todo", etc.
    val timestamp: Long = System.currentTimeMillis()
)
