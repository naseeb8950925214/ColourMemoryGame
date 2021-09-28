package com.example.colourmemory.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.colourmemory.data.model.CardModel

class ActivityViewModel : ViewModel() {
    private var savedHighScore: Int = 0
    private var savedCardList: ArrayList<CardModel>? = null

    fun getSavedHighScore(): Int {
        return savedHighScore
    }

    fun setSavedHighScore(savedHighScore: Int) {
        this.savedHighScore = savedHighScore
    }

    fun getSavedCardList(): ArrayList<CardModel>? {
        return savedCardList
    }

    fun setSavedCardList(savedCardList: ArrayList<CardModel>) {
        this.savedCardList = savedCardList
    }
}