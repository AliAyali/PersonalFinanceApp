package com.aliayali.personalfinanceapp.di

import android.content.Context
import androidx.room.Room
import com.aliayali.personalfinanceapp.data.local.database.MyDataBase
import com.aliayali.personalfinanceapp.data.local.database.dao.AccountDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): MyDataBase {
        return Room.databaseBuilder(
            context,
            MyDataBase::class.java,
            MyDataBase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAccountDao(database: MyDataBase): AccountDao {
        return database.accountDao()
    }
}