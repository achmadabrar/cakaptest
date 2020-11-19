package com.achmadabrar.cakaptestapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.achmadabrar.cakaptestapp.data.models.Contact

@Database(
    entities = [Contact::class],
    version = 1,
    exportSchema = true
)
abstract class ContactDatabase: RoomDatabase() {
    
    abstract fun contactDao(): ContactDao

    companion object {

        private var instance: ContactDatabase? = null
        private val LOCK = Any()
        const val DB_NAME = "contacts.db"

        @JvmStatic
        fun getInstance(context: Context): ContactDatabase {
            synchronized(LOCK) {
                if (instance == null) {
                    instance = Room
                        .databaseBuilder(
                            context.applicationContext,
                            ContactDatabase::class.java,
                            DB_NAME
                        )
                        .build()
                }
                return instance!!
            }
        }
    }
}