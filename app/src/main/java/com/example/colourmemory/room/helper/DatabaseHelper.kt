package com.example.colourmemory.room.helper

import com.example.colourmemory.room.model.PlayerScoreInfo

interface DatabaseHelper {
    suspend fun getAllPlayerScores(): List<PlayerScoreInfo>?
    suspend fun insertAll(playerScoreInfoList: List<PlayerScoreInfo>)
}