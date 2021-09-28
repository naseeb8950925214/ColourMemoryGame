package com.example.colourmemory.ui.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.colourmemory.R
import com.example.colourmemory.room.model.PlayerScoreInfo
import com.example.colourmemory.utils.Status
import kotlinx.android.synthetic.main.fragment_user_name_entry.*

class UserNameEntryFragment : BaseFragment(R.layout.fragment_user_name_entry) {
    private val TAG = UserNameEntryFragment::class.java.canonicalName
    private var mPlayerScore: Int? = null
    private var mPlayerName: String? = null
    private var mPlayerRank: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        initView()
        setupAllPlayerScoresObserver()
        setupInsertUserScoreInfoObserver()
    }

    private fun initView() {
        val args: UserNameEntryFragmentArgs by navArgs()
        mPlayerScore = args.playerScore
        Log.d(TAG, "initView playerScore : $mPlayerScore")
        submitBtn.setOnClickListener {
            Log.d(TAG, "initView submitBtn click")
            if (userNameET.text.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter valid name", Toast.LENGTH_LONG)
                    .show()
            } else {
                mPlayerName = userNameET.text.toString()
                getRoomDbViewModel().getAllPlayersScores()
            }
        }
    }

    private fun setupAllPlayerScoresObserver() {
        Log.d(TAG, "setupAllPlayerScoresObserver")
        getRoomDbViewModel().getAllPlayersScoresLiveData().observe(viewLifecycleOwner, { it ->
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    Log.d(
                        TAG,
                        "setupAllPlayerScoresObserver PlayerScoreInfo list : ${it.data}"
                    )
                    val playerScoreInfo = PlayerScoreInfo(
                        playerName = mPlayerName!!,
                        playerScore = mPlayerScore!!
                    )
                    val tempList = ArrayList<PlayerScoreInfo>()
                    mPlayerRank = if (!it.data.isNullOrEmpty()) {
                        tempList.addAll(it.data)
                        tempList.add(playerScoreInfo)
                        tempList.sortByDescending {
                            it.playerScore
                        }
                        if (tempList.indexOf(playerScoreInfo) == 0) 1 else tempList.indexOf(
                            playerScoreInfo
                        ) + 1
                    } else {
                        tempList.add(playerScoreInfo)
                        1
                    }
                    getRoomDbViewModel().insertAll(tempList)
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupInsertUserScoreInfoObserver() {
        Log.d(TAG, "setupInsertUserScoreInfoObserver")
        getRoomDbViewModel().getInsertPlayerScoreInfoLiveData()
            .observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        Log.d(
                            TAG,
                            "setupInsertUserScoreInfoObserver insertPlayerScoreCompleted : ${it.data}"
                        )
                        if (it.data == true) {
                            findNavController().navigate(
                                UserNameEntryFragmentDirections.actionUserNameEntryFragmentToUserScoreInfoFragment(
                                    mPlayerScore!!, mPlayerName!!, mPlayerRank!!
                                )
                            )
                        }
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            })
    }
}
