package com.example.a226complete.unit4.room

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao():UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database" // database file name
                )
                    .fallbackToDestructiveMigration() // Wipes & rebuilds on schema change (safe for dev)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}