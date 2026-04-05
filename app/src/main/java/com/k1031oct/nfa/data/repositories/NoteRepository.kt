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
    private val firestore: FirebaseFirestore,
    private val authRepository: AuthRepository
) {
    private fun getNotesCollection() = authRepository.getUserId()?.let { uid ->
        firestore.collection("users").document(uid).collection("notes")
    }

    fun getNotes(): Flow<List<Note>> {
        val collection = getNotesCollection() ?: return kotlinx.coroutines.flow.flowOf(emptyList())
        return collection.orderBy("timestamp").snapshots().map { snapshot ->
            snapshot.toObjects(Note::class.java)
        }
    }

    suspend fun saveNote(note: Note) {
        val collection = getNotesCollection() ?: throw Exception("User not logged in")
        if (note.id.isEmpty()) {
            val docRef = collection.document()
            val newNote = note.copy(id = docRef.id)
            docRef.set(newNote).await()
        } else {
            collection.document(note.id).set(note).await()
        }
    }

    suspend fun deleteNote(noteId: String) {
        val collection = getNotesCollection() ?: throw Exception("User not logged in")
        collection.document(noteId).delete().await()
    }
}
