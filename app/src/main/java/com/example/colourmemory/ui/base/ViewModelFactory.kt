package com.example.colourmemory.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.colourmemory.data.repository.RoomDbRepository
import com.example.colourmemory.room.database.DatabaseBuilder
import com.example.colourmemory.room.helper.DatabaseHelper
import com.example.colourmemory.room.helper.DatabaseHelperImpl
import com.example.colourmemory.ui.main.viewmodel.ActivityViewModel
import com.example.colourmemory.ui.main.viewmodel.RoomDbViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(RoomDbViewModel::class.java) -> {
                return RoomDbViewModel(RoomDbRepository(DatabaseHelperImpl(DatabaseBuilder.getInstance()))) as T
            }
            modelClass.isAssignableFrom(ActivityViewModel::class.java) -> {
                return ActivityViewModel() as T
            }
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }

}