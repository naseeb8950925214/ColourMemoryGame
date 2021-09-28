package com.example.colourmemory.ui.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.colourmemory.R
import com.example.colourmemory.data.model.CardModel
import com.example.colourmemory.ui.main.adapter.HomeAdapter
import com.example.colourmemory.ui.main.viewmodel.ActivityViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class HomeFragment : BaseFragment(R.layout.fragment_home), HomeAdapter.RecyclerViewEventsListener {
    private val TAG = HomeFragment::class.java.canonicalName
    private val mActivityViewModel: ActivityViewModel by activityViewModels()
    private lateinit var mHomeAdapter: HomeAdapter
    private var mCurrentScore: Int = 0
    private var mCardList: ArrayList<CardModel>? = null
    private var mIsGameEnded: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        initView()
    }

    private fun initView() {
        highScoresBtn.setOnClickListener {
            Log.d(TAG, "initView highScoresBtn click")
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToHighScoresFragment())
        }
        val savedCardList = mActivityViewModel.getSavedCardList()
        Log.d(TAG, "initView savedCardList : $savedCardList")
        mCardList = if (!mActivityViewModel.getSavedCardList().isNullOrEmpty())
            mActivityViewModel.getSavedCardList() else createCardList()
        Log.d(TAG, "initView final cardList : $mCardList")
        mCurrentScore = mActivityViewModel.getSavedHighScore()
        Log.d(TAG, "initView savedHighScore : $mCurrentScore")
        currentScoreTV.text = String.format(getString(R.string.score), mCurrentScore)
        gameRV.layoutManager = GridLayoutManager(requireContext(), 4)
        mHomeAdapter = HomeAdapter(mCardList!!, this)
        gameRV.adapter = mHomeAdapter
    }

    private fun createCardList(): ArrayList<CardModel> {
        Log.d(TAG, "createCardList")
        val cardList = ArrayList<CardModel>()
        for (i in 0..15) {
            cardList.add(CardModel(i % 8, getDrawable(cardNumber = i % 8)))
        }
        cardList.shuffle()
        return cardList
    }

    private fun getDrawable(cardNumber: Int): Int {
        Log.d(TAG, "getDrawable")
        return when (cardNumber) {
            0 -> R.drawable.colour1
            1 -> R.drawable.colour2
            2 -> R.drawable.colour3
            3 -> R.drawable.colour4
            4 -> R.drawable.colour5
            5 -> R.drawable.colour6
            6 -> R.drawable.colour7
            else -> R.drawable.colour8
        }
    }

    override fun onItemClickListener(
        firstSelectedCardModel: CardModel,
        secondSelectedCardModel: CardModel
    ) {
        Log.d(
            TAG,
            "onItemClickListener firstSelectedCardModel : $firstSelectedCardModel secondSelectedCardModel : $secondSelectedCardModel"
        )
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            withContext(Dispatchers.IO) {
                delay(1000)
            }
            if (firstSelectedCardModel.cardNumber == secondSelectedCardModel.cardNumber) {
                Log.d(
                    TAG,
                    "onItemClickListener called after delay match found"
                )
                mCurrentScore += 2
                mHomeAdapter.resetCards(true)
            } else {
                Log.d(
                    TAG,
                    "onItemClickListener called after delay mismatch found"
                )
                mCurrentScore--
                mHomeAdapter.resetCards(false)
            }
            currentScoreTV.text = String.format(getString(R.string.score), mCurrentScore)
        }
    }

    override fun onGameEnd() {
        Log.d(TAG, "onGameEnd mCurrentScore : $mCurrentScore")
        mIsGameEnded = true
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToUserNameEntryFragment(
                mCurrentScore
            )
        )
    }

    override fun onStop() {
        Log.d(TAG, "onStop mIsGameEnded : $mIsGameEnded")
        if (!mIsGameEnded) {
            Log.d(TAG, "onStop saving high score and card list to view model")
            mIsGameEnded = false
            mActivityViewModel.setSavedHighScore(mCurrentScore)
            mActivityViewModel.setSavedCardList(mCardList ?: arrayListOf())
        } else {
            Log.d(TAG, "onStop resetting high score and card list to view model")
            mActivityViewModel.setSavedHighScore(0)
            mActivityViewModel.setSavedCardList(arrayListOf())
        }
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
    }
}