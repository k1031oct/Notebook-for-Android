package com.k1031oct.nfa.ui.states;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b2\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u00e7\u0001\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u000e\u001a\u00020\n\u0012\b\b\u0002\u0010\u000f\u001a\u00020\f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\u0012\u001a\u00020\f\u0012\b\b\u0002\u0010\u0013\u001a\u00020\f\u0012\b\b\u0002\u0010\u0014\u001a\u00020\f\u0012\b\b\u0002\u0010\u0015\u001a\u00020\f\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\f\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u001b\u001a\u00020\f\u0012\b\b\u0002\u0010\u001c\u001a\u00020\n\u0012\b\b\u0002\u0010\u001d\u001a\u00020\n\u00a2\u0006\u0002\u0010\u001eJ\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\fH\u00c6\u0003J\t\u00107\u001a\u00020\fH\u00c6\u0003J\t\u00108\u001a\u00020\fH\u00c6\u0003J\t\u00109\u001a\u00020\fH\u00c6\u0003J\t\u0010:\u001a\u00020\fH\u00c6\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0017H\u00c6\u0003J\t\u0010<\u001a\u00020\fH\u00c6\u0003J\t\u0010=\u001a\u00020\u001aH\u00c6\u0003J\t\u0010>\u001a\u00020\fH\u00c6\u0003J\t\u0010?\u001a\u00020\nH\u00c6\u0003J\u000f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\t\u0010A\u001a\u00020\nH\u00c6\u0003J\u000f\u0010B\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H\u00c6\u0003J\t\u0010C\u001a\u00020\nH\u00c6\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\fH\u00c6\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0004H\u00c6\u0003J\t\u0010F\u001a\u00020\nH\u00c6\u0003J\t\u0010G\u001a\u00020\fH\u00c6\u0003J\t\u0010H\u001a\u00020\fH\u00c6\u0003J\u00eb\u0001\u0010I\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u0015\u001a\u00020\f2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\f2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\f2\b\b\u0002\u0010\u001c\u001a\u00020\n2\b\b\u0002\u0010\u001d\u001a\u00020\nH\u00c6\u0001J\u0013\u0010J\u001a\u00020\n2\b\u0010K\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010L\u001a\u00020MH\u00d6\u0001J\t\u0010N\u001a\u00020\fH\u00d6\u0001R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u000f\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0011\u0010\u0010\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0011\u0010\u0012\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010 R\u0011\u0010\u0018\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0011\u0010\u0014\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010 R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010*R\u0011\u0010\u001b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010 R\u0011\u0010\u000e\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010-R\u0011\u0010\u001c\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010-R\u0011\u0010\u001d\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010-R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010-R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010*R\u0011\u0010\u0015\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010 R\u0011\u0010\u0013\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010 R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0011\u0010\u0019\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u00104\u00a8\u0006O"}, d2 = {"Lcom/k1031oct/nfa/ui/states/NoteUiState;", "", "notes", "", "Lcom/k1031oct/nfa/data/models/Note;", "folders", "Lcom/k1031oct/nfa/data/models/Folder;", "history", "Lcom/k1031oct/nfa/data/models/HistoryItem;", "isLoading", "", "error", "", "selectedNote", "isEditing", "activeRefill", "activeSection", "activeFolderId", "calculatorDisplay", "selectedDate", "currentMonth", "searchQuery", "currentUser", "Lcom/google/firebase/auth/FirebaseUser;", "calendarViewType", "themeMode", "Lcom/k1031oct/nfa/ui/states/ThemeMode;", "holidayCountry", "isGoogleCalendarConnected", "isGoogleDocsConnected", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/lang/String;Lcom/k1031oct/nfa/data/models/Note;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/google/firebase/auth/FirebaseUser;Ljava/lang/String;Lcom/k1031oct/nfa/ui/states/ThemeMode;Ljava/lang/String;ZZ)V", "getActiveFolderId", "()Ljava/lang/String;", "getActiveRefill", "getActiveSection", "getCalculatorDisplay", "getCalendarViewType", "getCurrentMonth", "getCurrentUser", "()Lcom/google/firebase/auth/FirebaseUser;", "getError", "getFolders", "()Ljava/util/List;", "getHistory", "getHolidayCountry", "()Z", "getNotes", "getSearchQuery", "getSelectedDate", "getSelectedNote", "()Lcom/k1031oct/nfa/data/models/Note;", "getThemeMode", "()Lcom/k1031oct/nfa/ui/states/ThemeMode;", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class NoteUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.k1031oct.nfa.data.models.Note> notes = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.k1031oct.nfa.data.models.Folder> folders = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.k1031oct.nfa.data.models.HistoryItem> history = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    @org.jetbrains.annotations.Nullable()
    private final com.k1031oct.nfa.data.models.Note selectedNote = null;
    private final boolean isEditing = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String activeRefill = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String activeSection = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String activeFolderId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String calculatorDisplay = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String selectedDate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String currentMonth = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String searchQuery = null;
    @org.jetbrains.annotations.Nullable()
    private final com.google.firebase.auth.FirebaseUser currentUser = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String calendarViewType = null;
    @org.jetbrains.annotations.NotNull()
    private final com.k1031oct.nfa.ui.states.ThemeMode themeMode = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String holidayCountry = null;
    private final boolean isGoogleCalendarConnected = false;
    private final boolean isGoogleDocsConnected = false;
    
    public NoteUiState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.k1031oct.nfa.data.models.Note> notes, @org.jetbrains.annotations.NotNull()
    java.util.List<com.k1031oct.nfa.data.models.Folder> folders, @org.jetbrains.annotations.NotNull()
    java.util.List<com.k1031oct.nfa.data.models.HistoryItem> history, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.Nullable()
    com.k1031oct.nfa.data.models.Note selectedNote, boolean isEditing, @org.jetbrains.annotations.NotNull()
    java.lang.String activeRefill, @org.jetbrains.annotations.NotNull()
    java.lang.String activeSection, @org.jetbrains.annotations.Nullable()
    java.lang.String activeFolderId, @org.jetbrains.annotations.NotNull()
    java.lang.String calculatorDisplay, @org.jetbrains.annotations.NotNull()
    java.lang.String selectedDate, @org.jetbrains.annotations.NotNull()
    java.lang.String currentMonth, @org.jetbrains.annotations.NotNull()
    java.lang.String searchQuery, @org.jetbrains.annotations.Nullable()
    com.google.firebase.auth.FirebaseUser currentUser, @org.jetbrains.annotations.NotNull()
    java.lang.String calendarViewType, @org.jetbrains.annotations.NotNull()
    com.k1031oct.nfa.ui.states.ThemeMode themeMode, @org.jetbrains.annotations.NotNull()
    java.lang.String holidayCountry, boolean isGoogleCalendarConnected, boolean isGoogleDocsConnected) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.k1031oct.nfa.data.models.Note> getNotes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.k1031oct.nfa.data.models.Folder> getFolders() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.k1031oct.nfa.data.models.HistoryItem> getHistory() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.k1031oct.nfa.data.models.Note getSelectedNote() {
        return null;
    }
    
    public final boolean isEditing() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getActiveRefill() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getActiveSection() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getActiveFolderId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCalculatorDisplay() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSelectedDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentMonth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.auth.FirebaseUser getCurrentUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCalendarViewType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.k1031oct.nfa.ui.states.ThemeMode getThemeMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHolidayCountry() {
        return null;
    }
    
    public final boolean isGoogleCalendarConnected() {
        return false;
    }
    
    public final boolean isGoogleDocsConnected() {
        return false;
    }
    
    public NoteUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.k1031oct.nfa.data.models.Note> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.auth.FirebaseUser component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.k1031oct.nfa.ui.states.ThemeMode component17() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component18() {
        return null;
    }
    
    public final boolean component19() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.k1031oct.nfa.data.models.Folder> component2() {
        return null;
    }
    
    public final boolean component20() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.k1031oct.nfa.data.models.HistoryItem> component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.k1031oct.nfa.data.models.Note component6() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.k1031oct.nfa.ui.states.NoteUiState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.k1031oct.nfa.data.models.Note> notes, @org.jetbrains.annotations.NotNull()
    java.util.List<com.k1031oct.nfa.data.models.Folder> folders, @org.jetbrains.annotations.NotNull()
    java.util.List<com.k1031oct.nfa.data.models.HistoryItem> history, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.Nullable()
    com.k1031oct.nfa.data.models.Note selectedNote, boolean isEditing, @org.jetbrains.annotations.NotNull()
    java.lang.String activeRefill, @org.jetbrains.annotations.NotNull()
    java.lang.String activeSection, @org.jetbrains.annotations.Nullable()
    java.lang.String activeFolderId, @org.jetbrains.annotations.NotNull()
    java.lang.String calculatorDisplay, @org.jetbrains.annotations.NotNull()
    java.lang.String selectedDate, @org.jetbrains.annotations.NotNull()
    java.lang.String currentMonth, @org.jetbrains.annotations.NotNull()
    java.lang.String searchQuery, @org.jetbrains.annotations.Nullable()
    com.google.firebase.auth.FirebaseUser currentUser, @org.jetbrains.annotations.NotNull()
    java.lang.String calendarViewType, @org.jetbrains.annotations.NotNull()
    com.k1031oct.nfa.ui.states.ThemeMode themeMode, @org.jetbrains.annotations.NotNull()
    java.lang.String holidayCountry, boolean isGoogleCalendarConnected, boolean isGoogleDocsConnected) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}