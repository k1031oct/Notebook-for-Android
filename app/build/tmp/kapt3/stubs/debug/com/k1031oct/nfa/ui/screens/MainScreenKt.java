package com.k1031oct.nfa.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0007\u001a\u0012\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0007\u001a@\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u000bH\u0007\u001aF\u0010\r\u001a\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f2\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u000bH\u0007\u001a&\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015H\u0007\u001a$\u0010\u0016\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u000bH\u0007\u00a8\u0006\u0018"}, d2 = {"GridBackground", "", "MainScreen", "viewModel", "Lcom/k1031oct/nfa/ui/viewmodels/NoteViewModel;", "NoteCard", "note", "Lcom/k1031oct/nfa/data/models/Note;", "activeRefill", "", "onNoteClick", "Lkotlin/Function1;", "onDeleteClick", "NoteList", "notes", "", "RefillItem", "label", "isActive", "", "onClick", "Lkotlin/Function0;", "RefillSidebar", "onRefillSelected", "app_debug"})
public final class MainScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void MainScreen(@org.jetbrains.annotations.NotNull()
    com.k1031oct.nfa.ui.viewmodels.NoteViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void GridBackground() {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RefillSidebar(@org.jetbrains.annotations.NotNull()
    java.lang.String activeRefill, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onRefillSelected) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RefillItem(@org.jetbrains.annotations.NotNull()
    java.lang.String label, boolean isActive, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void NoteList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.k1031oct.nfa.data.models.Note> notes, @org.jetbrains.annotations.NotNull()
    java.lang.String activeRefill, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.k1031oct.nfa.data.models.Note, kotlin.Unit> onNoteClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.k1031oct.nfa.data.models.Note, kotlin.Unit> onDeleteClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void NoteCard(@org.jetbrains.annotations.NotNull()
    com.k1031oct.nfa.data.models.Note note, @org.jetbrains.annotations.NotNull()
    java.lang.String activeRefill, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.k1031oct.nfa.data.models.Note, kotlin.Unit> onNoteClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.k1031oct.nfa.data.models.Note, kotlin.Unit> onDeleteClick) {
    }
}