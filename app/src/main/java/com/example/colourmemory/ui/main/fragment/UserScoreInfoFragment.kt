package com.example.colourmemory.ui.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.colourmemory.R
import kotlinx.android.synthetic.main.fragment_user_score_info.*

class UserScoreInfoFragment : BaseFragment(R.layout.fragment_user_score_info) {
    private val TAG = UserNameEntryFragment::class.java.canonicalName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        initView()
    }

    private fun initView() {
        val args: UserScoreInfoFragmentArgs by navArgs()
        val playerName = args.playerName
        val playerScore = args.playerScore
        val playerRank = args.playerRank
        Log.d(
            TAG,
            "initView playerName : $playerName playerScore : $playerScore playerRank : $playerRank"
        )
        userNameTV.text = String.format(getString(R.string.hi), playerName)
        userScoreTV.text = String.format(getString(R.string.your_score), playerScore)
        userRankTV.text = String.format(getString(R.string.your_rank), playerRank)
        okBtn.setOnClickListener {
            Log.d(TAG, "initView submitBtn onClick")
            findNavController().navigate(UserScoreInfoFragmentDirections.actionUserScoreInfoFragmentToHomeFragment())
        }
    }
}