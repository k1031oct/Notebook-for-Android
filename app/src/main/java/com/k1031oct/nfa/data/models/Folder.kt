package com.k1031oct.nfa.data.models

data class Folder(
    val id: String = "",
    val name: String = "",
    val color: Int = 0xFFFF5E62.toInt(), // デフォルト色
    val timestamp: Long = System.currentTimeMillis(),
    val isLocked: Boolean = false
)
