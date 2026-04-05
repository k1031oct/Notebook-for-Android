package com.k1031oct.nfa.ui.viewmodels;

import com.k1031oct.nfa.data.repositories.AuthRepository;
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

  private final Provider<AuthRepository> authRepositoryProvider;

  public NoteViewModel_Factory(Provider<NoteRepository> repositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    this.repositoryProvider = repositoryProvider;
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public NoteViewModel get() {
    return newInstance(repositoryProvider.get(), authRepositoryProvider.get());
  }

  public static NoteViewModel_Factory create(Provider<NoteRepository> repositoryProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    return new NoteViewModel_Factory(repositoryProvider, authRepositoryProvider);
  }

  public static NoteViewModel newInstance(NoteRepository repository,
      AuthRepository authRepository) {
    return new NoteViewModel(repository, authRepository);
  }
}
