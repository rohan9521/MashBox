package com.example.mashbox.UI

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FileLocationEntity::class],version = 1)
abstract class RoomFileLocationDataBase: RoomDatabase(){

    abstract fun getdDao(): FileLocationDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: RoomFileLocationDataBase? = null

        fun getDatabase(context: Context):RoomFileLocationDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                   RoomFileLocationDataBase::class.java,
                    "MusicFileLocationTable"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}