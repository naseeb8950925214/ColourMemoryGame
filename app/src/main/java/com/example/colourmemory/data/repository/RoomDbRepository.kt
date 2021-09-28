package com.example.colourmemory.data.repository

import android.util.Log
import com.example.colourmemory.room.helper.DatabaseHelper
import com.example.colourmemory.room.model.PlayerScoreInfo
import com.example.colourmemory.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RoomDbRepository(private val dbHelper: DatabaseHelper) {
    private val TAG = RoomDbRepository::class.java.canonicalName

    fun getAllPlayerScores(): Flow<Resource<List<PlayerScoreInfo>?>> {
        Log.d(TAG, "getAllPlayerScores")
        return flow {
            emit(Resource.loading(null))
            val allPlayerScoreList = dbHelper.getAllPlayerScores()
            Log.d(TAG, "getAllPlayerScores allPlayerScoreList : $allPlayerScoreList")
            emit(Resource.success(allPlayerScoreList))
        }.flowOn(Dispatchers.IO)
    }

    fun insertAll(playerScoreInfoList: List<PlayerScoreInfo>): Flow<Resource<Boolean>> {
        Log.d(TAG, "insertPlayerScoreInfo playerScoreInfoList : $playerScoreInfoList")
        return flow {
            emit(Resource.loading(null))
            dbHelper.insertAll(playerScoreInfoList)
            emit(Resource.success(true))
        }.flowOn(Dispatchers.IO)
    }
}