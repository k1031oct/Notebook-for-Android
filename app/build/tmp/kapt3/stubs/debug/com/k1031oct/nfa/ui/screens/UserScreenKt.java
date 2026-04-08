package com.k1031oct.nfa.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0007\u001a,\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\tH\u0007\u001a6\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\t\u00a2\u0006\u0002\b\u0010\u00a2\u0006\u0002\b\u0011H\u0007\u001a\u0010\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0014H\u0007\u00a8\u0006\u0015"}, d2 = {"InfoRow", "", "label", "", "value", "IntegrationRow", "enabled", "", "onToggle", "Lkotlin/Function1;", "SettingsCard", "title", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "content", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "UserScreen", "viewModel", "Lcom/k1031oct/nfa/ui/viewmodels/NoteViewModel;", "app_debug"})
public final class UserScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void UserScreen(@org.jetbrains.annotations.NotNull()
    com.k1031oct.nfa.ui.viewmodels.NoteViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SettingsCard(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.graphics.vector.ImageVector icon, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super androidx.compose.foundation.layout.ColumnScope, kotlin.Unit> content) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void IntegrationRow(@org.jetbrains.annotations.NotNull()
    java.lang.String label, boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onToggle) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void InfoRow(@org.jetbrains.annotations.NotNull()
    java.lang.String label, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
}