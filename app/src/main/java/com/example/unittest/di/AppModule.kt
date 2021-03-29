package com.example.unittest.di

import android.content.Context
import androidx.room.Room
import com.example.unittest.db.UserDao
import com.example.unittest.db.UserDatabase
import com.example.unittest.main.DefaultMainRepository
import com.example.unittest.main.MainRepository
import com.example.unittest.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Created by SARATH on 29-03-2021
 */

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.getUserDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
            "user_data.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMainRepository(db : UserDatabase) : MainRepository = DefaultMainRepository(db)

    @Singleton
    @Provides
    fun provideDispatchers() : DispatcherProvider = object : DispatcherProvider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }
}