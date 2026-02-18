package com.aliayali.personalfinanceapp.di

import com.aliayali.personalfinanceapp.data.repository.AccountRepositoryImpl
import com.aliayali.personalfinanceapp.domain.repository.AccountRepository
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
}