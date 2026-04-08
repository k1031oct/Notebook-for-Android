package com.k1031oct.nfa.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.k1031oct.nfa.data.models.Note
import com.k1031oct.nfa.data.models.Folder
import com.k1031oct.nfa.data.models.HistoryItem
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

    private fun getFoldersCollection() = authRepository.getUserId()?.let { uid ->
        firestore.collection("users").document(uid).collection("folders")
    }

    private fun getHistoryCollection() = authRepository.getUserId()?.let { uid ->
        firestore.collection("users").document(uid).collection("history")
    }

    fun getNotes(): Flow<List<Note>> {
        val collection = getNotesCollection() ?: return kotlinx.coroutines.flow.flowOf(emptyList())
        return collection.orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .snapshots().map { snapshot ->
                snapshot.toObjects(Note::class.java)
            }
    }

    fun getFolders(): Flow<List<Folder>> {
        val collection = getFoldersCollection() ?: return kotlinx.coroutines.flow.flowOf(emptyList())
        return collection.orderBy("timestamp").snapshots().map { snapshot ->
            snapshot.toObjects(Folder::class.java)
        }
    }

    fun getHistory(): Flow<List<HistoryItem>> {
        val collection = getHistoryCollection() ?: return kotlinx.coroutines.flow.flowOf(emptyList())
        return collection.orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .limit(50).snapshots().map { snapshot ->
                snapshot.toObjects(HistoryItem::class.java)
            }
    }

    suspend fun saveNote(note: Note) {
        val collection = getNotesCollection() ?: throw Exception("User not logged in")
        val isNew = note.id.isEmpty()
        if (isNew) {
            val docRef = collection.document()
            val newNote = note.copy(id = docRef.id)
            docRef.set(newNote).await()
            saveHistory(HistoryItem(noteId = docRef.id, noteTitle = note.title, action = "Created"))
        } else {
            collection.document(note.id).set(note).await()
            saveHistory(HistoryItem(noteId = note.id, noteTitle = note.title, action = "Updated"))
        }
    }

    suspend fun saveFolder(folder: Folder) {
        val collection = getFoldersCollection() ?: throw Exception("User not logged in")
        if (folder.id.isEmpty()) {
            val docRef = collection.document()
            collection.document(docRef.id).set(folder.copy(id = docRef.id)).await()
        } else {
            collection.document(folder.id).set(folder).await()
        }
    }

    suspend fun saveHistory(history: HistoryItem) {
        val collection = getHistoryCollection() ?: return
        val docRef = collection.document()
        collection.document(docRef.id).set(history.copy(id = docRef.id)).await()
    }

    suspend fun deleteNote(noteId: String, title: String = "") {
        val collection = getNotesCollection() ?: throw Exception("User not logged in")
        collection.document(noteId).delete().await()
        saveHistory(HistoryItem(noteId = noteId, noteTitle = title, action = "Deleted"))
    }

    suspend fun deleteFolder(folderId: String) {
        val folderCollection = getFoldersCollection() ?: throw Exception("User not logged in")
        val notesCollection = getNotesCollection() ?: throw Exception("User not logged in")

        // Get notes in this folder
        val notesInFolder = notesCollection.whereEqualTo("folderId", folderId).get().await()
        
        // Batch update notes to remove folder reference
        val batch = firestore.batch()
        notesInFolder.documents.forEach { doc ->
            batch.update(doc.reference, "folderId", null)
        }
        batch.commit().await()

        // Delete folder
        folderCollection.document(folderId).delete().await()
    }
}
