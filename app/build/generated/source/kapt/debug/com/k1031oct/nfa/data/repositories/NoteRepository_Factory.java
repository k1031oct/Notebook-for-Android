package com.k1031oct.nfa.data.repositories;

import com.google.firebase.firestore.FirebaseFirestore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class NoteRepository_Factory implements Factory<NoteRepository> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  public NoteRepository_Factory(Provider<FirebaseFirestore> firestoreProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    this.firestoreProvider = firestoreProvider;
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public NoteRepository get() {
    return newInstance(firestoreProvider.get(), authRepositoryProvider.get());
  }

  public static NoteRepository_Factory create(Provider<FirebaseFirestore> firestoreProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    return new NoteRepository_Factory(firestoreProvider, authRepositoryProvider);
  }

  public static NoteRepository newInstance(FirebaseFirestore firestore,
      AuthRepository authRepository) {
    return new NoteRepository(firestore, authRepository);
  }
}
