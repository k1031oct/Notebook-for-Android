package com.k1031oct.nfa.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006JD\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b0\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/k1031oct/nfa/util/BiometricHelper;", "", "()V", "canAuthenticate", "", "context", "Landroid/content/Context;", "showBiometricPrompt", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "title", "", "subtitle", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "app_debug"})
public final class BiometricHelper {
    @org.jetbrains.annotations.NotNull()
    public static final com.k1031oct.nfa.util.BiometricHelper INSTANCE = null;
    
    private BiometricHelper() {
        super();
    }
    
    public final boolean canAuthenticate(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final void showBiometricPrompt(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentActivity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String subtitle, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onError) {
    }
}