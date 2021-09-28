package com.example.colourmemory.ui.main.viewholder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.colourmemory.room.model.PlayerScoreInfo
import kotlinx.android.synthetic.main.view_holder_high_scores.view.*
import java.text.FieldPosition

class HighScoresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val TAG = HighScoresViewHolder::class.java.canonicalName

    fun bind(playerScoreModel: PlayerScoreInfo, position: Int) {
        Log.d(TAG, "bind")
        itemView.rankValueTV.text = (position + 1).toString()
        itemView.nameValueTV.text = playerScoreModel.playerName
        itemView.scoreValueTV.text = playerScoreModel.playerScore!!.toString()
    }
}