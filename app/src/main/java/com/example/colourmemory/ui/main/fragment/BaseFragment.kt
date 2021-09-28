package com.example.colourmemory.ui.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.colourmemory.ui.base.ViewModelFactory
import com.example.colourmemory.ui.main.viewmodel.RoomDbViewModel

open class BaseFragment(fragmentId: Int) : Fragment(fragmentId) {
    private val TAG = BaseFragment::class.java.canonicalName
    private lateinit var mRoomDbViewModel: RoomDbViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        mRoomDbViewModel =
            ViewModelProvider(this, ViewModelFactory()).get(RoomDbViewModel::class.java)
    }

    fun getRoomDbViewModel(): RoomDbViewModel {
        Log.d(TAG, "getRoomDbViewModel")
        return mRoomDbViewModel
    }
}