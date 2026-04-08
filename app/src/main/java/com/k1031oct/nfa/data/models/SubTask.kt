package com.k1031oct.nfa.data.models

data class SubTask(
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String = "",
    val isCompleted: Boolean = false
)
