package com.example.colourmemory.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.colourmemory.R
import com.example.colourmemory.room.model.PlayerScoreInfo
import com.example.colourmemory.ui.main.viewholder.HighScoresViewHolder

class HighScoresListAdapter(private val playerScoreList: ArrayList<PlayerScoreInfo>) :
    RecyclerView.Adapter<HighScoresViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HighScoresViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_high_scores, parent,
                false
            )
        )

    override fun getItemCount(): Int = playerScoreList.size

    override fun onBindViewHolder(holder: HighScoresViewHolder, position: Int) {
        holder.bind(playerScoreList[position], position)
    }

    fun addData(playerScoreInfoList: List<PlayerScoreInfo>) {
        this.playerScoreList.clear()
        this.playerScoreList.addAll(playerScoreInfoList)
        notifyDataSetChanged()
    }
}