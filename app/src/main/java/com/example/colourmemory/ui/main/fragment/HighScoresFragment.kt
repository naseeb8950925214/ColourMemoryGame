package com.example.colourmemory.ui.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colourmemory.R
import com.example.colourmemory.room.model.PlayerScoreInfo
import com.example.colourmemory.ui.main.adapter.HighScoresListAdapter
import com.example.colourmemory.utils.Status
import kotlinx.android.synthetic.main.fragment_high_scores.*
import kotlinx.android.synthetic.main.fragment_user_name_entry.progressBar

class HighScoresFragment : BaseFragment(R.layout.fragment_high_scores) {
    private val TAG = HighScoresFragment::class.java.canonicalName
    private lateinit var mHighScoresListAdapter: HighScoresListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        setupAllPlayerScoresObserver()
        initView()
        getRoomDbViewModel().getAllPlayersScores()
    }

    private fun initView() {
        Log.d(TAG, "initView")
        mHighScoresListAdapter = HighScoresListAdapter(arrayListOf())
        val dividerItemDecoration = DividerItemDecoration(
            highScoresRV.context,
            (highScoresRV.layoutManager as LinearLayoutManager).orientation
        )
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.recycler_view_divider
            )!!
        )
        highScoresRV.addItemDecoration(dividerItemDecoration)
        highScoresRV.adapter = mHighScoresListAdapter
    }

    private fun setupAllPlayerScoresObserver() {
        Log.d(TAG, "setupAllPlayerScoresObserver")
        getRoomDbViewModel().getAllPlayersScoresLiveData().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    Log.d(
                        TAG,
                        "setupAllPlayerScoresObserver PlayerScoreInfo list : ${it.data}"
                    )
                    if (!it.data.isNullOrEmpty()) {
                        val tempList = ArrayList<PlayerScoreInfo>()
                        tempList.addAll(it.data)
                        tempList.sortByDescending {
                            it.playerScore
                        }
                        mHighScoresListAdapter.addData(tempList)
                    } else {
                        Toast.makeText(requireContext(), "No high scores found", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

}