package com.k1031oct.nfa.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.k1031oct.nfa.data.models.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val notesCollection = firestore.collection("notes")

    fun getNotes(): Flow<List<Note>> {
        return notesCollection.orderBy("timestamp").snapshots().map { snapshot ->
            snapshot.toObjects(Note::class.java)
        }
    }

    suspend fun saveNote(note: Note) {
        if (note.id.isEmpty()) {
            val docRef = notesCollection.document()
            val newNote = note.copy(id = docRef.id)
            docRef.set(newNote).await()
        } else {
            notesCollection.document(note.id).set(note).await()
        }
    }

    suspend fun deleteNote(noteId: String) {
        notesCollection.document(noteId).delete().await()
    }
}
