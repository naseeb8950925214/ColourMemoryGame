package com.example.colourmemory.ui.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.colourmemory.R
import com.example.colourmemory.ui.base.ViewModelFactory
import com.example.colourmemory.ui.main.viewmodel.ActivityViewModel
import com.example.colourmemory.ui.main.viewmodel.RoomDbViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}