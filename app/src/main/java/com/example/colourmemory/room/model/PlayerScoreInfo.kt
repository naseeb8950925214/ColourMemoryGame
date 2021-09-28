package com.example.colourmemory.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.colourmemory.utils.AppConstants

@Entity(tableName = AppConstants.Database.TableName.PLAYER_SCORE)
data class PlayerScoreInfo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "player_name")
    var playerName: String? = null,
    @ColumnInfo(name = "player_score")
    var playerScore: Int? = null
)
