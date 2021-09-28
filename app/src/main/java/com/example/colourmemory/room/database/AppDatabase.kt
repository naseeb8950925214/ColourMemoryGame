package com.example.colourmemory.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.colourmemory.room.dao.PlayerScoreDao
import com.example.colourmemory.room.model.PlayerScoreInfo

@Database(entities = [PlayerScoreInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playerScoreDao(): PlayerScoreDao
}