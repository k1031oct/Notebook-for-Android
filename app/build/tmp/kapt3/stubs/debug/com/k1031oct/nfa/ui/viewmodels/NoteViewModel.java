package com.k1031oct.nfa.ui.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J \u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u000bH\u0002J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000bJ\u000e\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u000bJ\u0006\u0010\u001c\u001a\u00020\u0018J\u000e\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u000bJ\u000e\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u000bJ\u0010\u0010$\u001a\u00020\u00182\b\u0010 \u001a\u0004\u0018\u00010!J\u000e\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\u000bJ\u001c\u0010\'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020\u000b2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00180*J\u0006\u0010+\u001a\u00020\u0018J\u000e\u0010,\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006-"}, d2 = {"Lcom/k1031oct/nfa/ui/viewmodels/NoteViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/k1031oct/nfa/data/repositories/NoteRepository;", "authRepository", "Lcom/k1031oct/nfa/data/repositories/AuthRepository;", "(Lcom/k1031oct/nfa/data/repositories/NoteRepository;Lcom/k1031oct/nfa/data/repositories/AuthRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/k1031oct/nfa/ui/states/NoteUiState;", "calculatorInput", "", "calculatorLastValue", "", "calculatorPendingOp", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "calculate", "val1", "val2", "op", "changeRefill", "", "refillType", "deleteNote", "noteId", "loadNotes", "onCalculatorAction", "action", "saveNote", "note", "Lcom/k1031oct/nfa/data/models/Note;", "selectDate", "date", "selectNote", "showSection", "section", "signInWithGoogle", "idToken", "onSuccess", "Lkotlin/Function0;", "signOut", "toggleNoteCompletion", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class NoteViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.k1031oct.nfa.data.repositories.NoteRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.k1031oct.nfa.data.repositories.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.k1031oct.nfa.ui.states.NoteUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.k1031oct.nfa.ui.states.NoteUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String calculatorInput = "";
    private double calculatorLastValue = 0.0;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String calculatorPendingOp = "";
    
    @javax.inject.Inject()
    public NoteViewModel(@org.jetbrains.annotations.NotNull()
    com.k1031oct.nfa.data.repositories.NoteRepository repository, @org.jetbrains.annotations.NotNull()
    com.k1031oct.nfa.data.repositories.AuthRepository authRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.k1031oct.nfa.ui.states.NoteUiState> getUiState() {
        return null;
    }
    
    public final void loadNotes() {
    }
    
    public final void selectNote(@org.jetbrains.annotations.Nullable()
    com.k1031oct.nfa.data.models.Note note) {
    }
    
    public final void saveNote(@org.jetbrains.annotations.NotNull()
    com.k1031oct.nfa.data.models.Note note) {
    }
    
    public final void deleteNote(@org.jetbrains.annotations.NotNull()
    java.lang.String noteId) {
    }
    
    public final void changeRefill(@org.jetbrains.annotations.NotNull()
    java.lang.String refillType) {
    }
    
    public final void showSection(@org.jetbrains.annotations.NotNull()
    java.lang.String section) {
    }
    
    public final void selectDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
    }
    
    public final void toggleNoteCompletion(@org.jetbrains.annotations.NotNull()
    com.k1031oct.nfa.data.models.Note note) {
    }
    
    public final void signInWithGoogle(@org.jetbrains.annotations.NotNull()
    java.lang.String idToken, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess) {
    }
    
    public final void signOut() {
    }
    
    public final void onCalculatorAction(@org.jetbrains.annotations.NotNull()
    java.lang.String action) {
    }
    
    private final double calculate(double val1, double val2, java.lang.String op) {
        return 0.0;
    }
}