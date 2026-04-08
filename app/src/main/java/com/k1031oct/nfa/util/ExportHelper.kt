package com.k1031oct.nfa.util

import android.content.Context
import android.content.Intent
import com.k1031oct.nfa.data.models.Note

object ExportHelper {
    fun shareNoteAsText(context: Context, note: Note) {
        val shareText = """
            ${note.title}
            ---
            ${note.content}
            
            Tags: ${note.tags.joinToString(", ")}
            Created via Notebook Premium
        """.trimIndent()

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, note.title)
            putExtra(Intent.EXTRA_TEXT, shareText)
        }

        context.startActivity(Intent.createChooser(intent, "Share Note via"))
    }
}
