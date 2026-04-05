package com.k1031oct.nfa.ui.viewmodels;

import com.k1031oct.nfa.data.repositories.NoteRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class NoteViewModel_Factory implements Factory<NoteViewModel> {
  private final Provider<NoteRepository> repositoryProvider;

  public NoteViewModel_Factory(Provider<NoteRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public NoteViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static NoteViewModel_Factory create(Provider<NoteRepository> repositoryProvider) {
    return new NoteViewModel_Factory(repositoryProvider);
  }

  public static NoteViewModel newInstance(NoteRepository repository) {
    return new NoteViewModel(repository);
  }
}
