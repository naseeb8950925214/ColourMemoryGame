package com.example.colourmemory.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.colourmemory.R
import com.example.colourmemory.data.model.CardModel
import com.example.colourmemory.ui.main.viewholder.HomeViewHolder

class HomeAdapter(
    private val cardModelList: ArrayList<CardModel>,
    private val recyclerViewEventsListener: RecyclerViewEventsListener
) : RecyclerView.Adapter<HomeViewHolder>() {
    private val TAG = HomeAdapter::class.java.canonicalName
    private var mFirstSelectedItemPosition: Int = -1
    private var mSecondSelectedItemPosition: Int = -1
    private var mIsClickAllowed = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_home, parent,
                false
            )
        )

    override fun getItemCount(): Int = cardModelList.size

    override fun onBindViewHolder(
        holder: HomeViewHolder,
        position: Int
    ) {
        holder.itemView.setOnClickListener {
            if (!mIsClickAllowed) {
                return@setOnClickListener
            }
            if (mFirstSelectedItemPosition == -1) {
                mFirstSelectedItemPosition = holder.adapterPosition
                redrawPosition(holder.adapterPosition)
                return@setOnClickListener
            }
            mSecondSelectedItemPosition = holder.adapterPosition
            redrawPosition(holder.adapterPosition)
            mIsClickAllowed = false
            recyclerViewEventsListener.onItemClickListener(
                cardModelList[mFirstSelectedItemPosition],
                cardModelList[mSecondSelectedItemPosition]
            )
        }
        holder.bind(
            cardModelList[position],
            mFirstSelectedItemPosition != position && mSecondSelectedItemPosition != position
        )
    }

    fun resetCards(isNeedToRemoveCards: Boolean) {
        Log.d(
            TAG,
            "resetCards isNeedToRemoveCards : $isNeedToRemoveCards mFirstSelectedItemPosition : $mFirstSelectedItemPosition" +
                    " mSecondSelectedItemPosition : $mSecondSelectedItemPosition"
        )
        mIsClickAllowed = true
        val firstSelectedItemPositionTemp = mFirstSelectedItemPosition
        var secondSelectedItemPositionTemp = mSecondSelectedItemPosition
        if (isNeedToRemoveCards) {
            secondSelectedItemPositionTemp =
                if (mSecondSelectedItemPosition > mFirstSelectedItemPosition) {
                    mSecondSelectedItemPosition - 1
                } else mSecondSelectedItemPosition
            cardModelList.removeAt(mFirstSelectedItemPosition)
            cardModelList.removeAt(secondSelectedItemPositionTemp)
            mFirstSelectedItemPosition = -1
            mSecondSelectedItemPosition = -1
            removePosition(firstSelectedItemPositionTemp)
            removePosition(secondSelectedItemPositionTemp)
            if (cardModelList.isEmpty()) {
                recyclerViewEventsListener.onGameEnd()
            }
            Log.d(
                TAG,
                "resetCards cardListSize : ${cardModelList.size}"
            )
        } else {
            mFirstSelectedItemPosition = -1
            mSecondSelectedItemPosition = -1
            redrawPosition(firstSelectedItemPositionTemp)
            redrawPosition(secondSelectedItemPositionTemp)
        }
    }

    private fun redrawPosition(positionToBeRedrawn: Int) {
        notifyItemChanged(positionToBeRedrawn)
    }

    private fun removePosition(positionToBeRemoved: Int) {
        notifyItemRemoved(positionToBeRemoved)
    }

    interface RecyclerViewEventsListener {
        fun onItemClickListener(
            firstSelectedCardModel: CardModel,
            secondSelectedCardModel: CardModel
        )

        fun onGameEnd()
    }
}