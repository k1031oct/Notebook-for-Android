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

  public NoteRepository_Factory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public NoteRepository get() {
    return newInstance(firestoreProvider.get());
  }

  public static NoteRepository_Factory create(Provider<FirebaseFirestore> firestoreProvider) {
    return new NoteRepository_Factory(firestoreProvider);
  }

  public static NoteRepository newInstance(FirebaseFirestore firestore) {
    return new NoteRepository(firestore);
  }
}
