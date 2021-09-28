package com.example.colourmemory.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.colourmemory.data.repository.RoomDbRepository
import com.example.colourmemory.room.model.PlayerScoreInfo
import com.example.colourmemory.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RoomDbViewModel(private val roomDbRepository: RoomDbRepository) : ViewModel() {
    private val TAG = RoomDbViewModel::class.java.canonicalName
    private var mAllPlayersScoresLiveData: MutableLiveData<Resource<List<PlayerScoreInfo>?>> =
        MutableLiveData()
    private var mInsertPlayerScoreCompletedLiveData: MutableLiveData<Resource<Boolean>> =
        MutableLiveData()

    fun getAllPlayersScoresLiveData(): LiveData<Resource<List<PlayerScoreInfo>?>> =
        mAllPlayersScoresLiveData

    fun getAllPlayersScores() {
        Log.d(TAG, "getAllPlayersScores")
        viewModelScope.launch {
            roomDbRepository.getAllPlayerScores().collect {
                mAllPlayersScoresLiveData.postValue(it)
            }
        }
    }

    fun getInsertPlayerScoreInfoLiveData(): LiveData<Resource<Boolean>> =
        mInsertPlayerScoreCompletedLiveData

    fun insertAll(playerScoreInfoList: List<PlayerScoreInfo>) {
        Log.d(TAG, "insertAll playerScoreInfoList : $playerScoreInfoList")
        viewModelScope.launch {
            roomDbRepository.insertAll(playerScoreInfoList).collect {
                mInsertPlayerScoreCompletedLiveData.postValue(it)
            }
        }
    }
}