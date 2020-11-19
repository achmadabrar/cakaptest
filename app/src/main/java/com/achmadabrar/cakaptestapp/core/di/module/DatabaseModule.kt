package com.achmadabrar.cakaptestapp.core.di.module

import android.app.Application
import androidx.room.Room
import com.achmadabrar.cakaptestapp.data.database.ContactDao
import com.achmadabrar.cakaptestapp.data.database.ContactDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): ContactDatabase {
        return Room
            .databaseBuilder(application, ContactDatabase::class.java, ContactDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideUserDao(appDataBase: ContactDatabase): ContactDao {
        return appDataBase.contactDao()
    }
}