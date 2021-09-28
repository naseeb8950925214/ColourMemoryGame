package com.example.colourmemory.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.colourmemory.room.model.PlayerScoreInfo
import com.example.colourmemory.utils.AppConstants

@Dao
interface PlayerScoreDao {
    @Query("SELECT * FROM ${AppConstants.Database.TableName.PLAYER_SCORE}") //ORDER BY UPPER(player_score) DESC
    suspend fun getAllPlayerScores(): List<PlayerScoreInfo>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(playerScoreInfoList: List<PlayerScoreInfo>)
}