package com.example.colourmemory.room.helper

import com.example.colourmemory.room.database.AppDatabase
import com.example.colourmemory.room.model.PlayerScoreInfo

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getAllPlayerScores(): List<PlayerScoreInfo>? =
        appDatabase.playerScoreDao().getAllPlayerScores()

    override suspend fun insertAll(playerScoreInfoList: List<PlayerScoreInfo>) =
        appDatabase.playerScoreDao().insertAll(playerScoreInfoList)
}