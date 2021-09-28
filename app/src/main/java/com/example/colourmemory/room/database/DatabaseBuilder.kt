package com.example.colourmemory.room.database

import android.util.Log
import androidx.room.Room
import com.example.colourmemory.App
import com.example.colourmemory.utils.AppConstants

object DatabaseBuilder {
    private val TAG = DatabaseBuilder::class.java.canonicalName
    private var INSTANCE: AppDatabase? = null

    fun getInstance(): AppDatabase {
        Log.d(TAG, "getInstance")
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = buildRoomDB()
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(): AppDatabase {
        Log.d(TAG, "buildRoomDB")
        return Room.databaseBuilder(
            App.getInstance(),
            AppDatabase::class.java,
            AppConstants.Database.DATABASE_NAME
        ).build()
    }
}