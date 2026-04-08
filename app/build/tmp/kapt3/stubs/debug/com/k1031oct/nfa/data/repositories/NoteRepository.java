package com.k1031oct.nfa.data.repositories;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000bJ \u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002J\u0012\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00120\u0011J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0015H\u0002J\u0012\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00120\u0011J\n\u0010\u001b\u001a\u0004\u0018\u00010\u0015H\u0002J\u0016\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010$R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/k1031oct/nfa/data/repositories/NoteRepository;", "", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "authRepository", "Lcom/k1031oct/nfa/data/repositories/AuthRepository;", "(Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/k1031oct/nfa/data/repositories/AuthRepository;)V", "deleteFolder", "", "folderId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteNote", "noteId", "title", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFolders", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/k1031oct/nfa/data/models/Folder;", "getFoldersCollection", "Lcom/google/firebase/firestore/CollectionReference;", "getHistory", "Lcom/k1031oct/nfa/data/models/HistoryItem;", "getHistoryCollection", "getNotes", "Lcom/k1031oct/nfa/data/models/Note;", "getNotesCollection", "saveFolder", "folder", "(Lcom/k1031oct/nfa/data/models/Folder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveHistory", "history", "(Lcom/k1031oct/nfa/data/models/HistoryItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveNote", "note", "(Lcom/k1031oct/nfa/data/models/Note;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class NoteRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.k1031oct.nfa.data.repositories.AuthRepository authRepository = null;
    
    @javax.inject.Inject()
    public NoteRepository(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore, @org.jetbrains.annotations.NotNull()
    com.k1031oct.nfa.data.repositories.AuthRepository authRepository) {
        super();
    }
    
    private final com.google.firebase.firestore.CollectionReference getNotesCollection() {
        return null;
    }
    
    private final com.google.firebase.firestore.CollectionReference getFoldersCollection() {
        return null;
    }
    
    private final com.google.firebase.firestore.CollectionReference getHistoryCollection() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.k1031oct.nfa.data.models.Note>> getNotes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.k1031oct.nfa.data.models.Folder>> getFolders() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.k1031oct.nfa.data.models.HistoryItem>> getHistory() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveNote(@org.jetbrains.annotations.NotNull()
    com.k1031oct.nfa.data.models.Note note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveFolder(@org.jetbrains.annotations.NotNull()
    com.k1031oct.nfa.data.models.Folder folder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveHistory(@org.jetbrains.annotations.NotNull()
    com.k1031oct.nfa.data.models.HistoryItem history, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteNote(@org.jetbrains.annotations.NotNull()
    java.lang.String noteId, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteFolder(@org.jetbrains.annotations.NotNull()
    java.lang.String folderId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}