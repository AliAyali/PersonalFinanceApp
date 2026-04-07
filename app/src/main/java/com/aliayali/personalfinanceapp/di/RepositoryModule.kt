package com.aliayali.personalfinanceapp.di

import com.aliayali.personalfinanceapp.data.repository.AccountRepositoryImpl
import com.aliayali.personalfinanceapp.data.repository.NoteRepositoryImpl
import com.aliayali.personalfinanceapp.domain.repository.AccountRepository
import com.aliayali.personalfinanceapp.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAccountRepository(
        accountRepositoryImpl: AccountRepositoryImpl,
    ): AccountRepository

    @Binds
    abstract fun bindNoteRepository(
        noteRepositoryImpl: NoteRepositoryImpl,
    ): NoteRepository
}