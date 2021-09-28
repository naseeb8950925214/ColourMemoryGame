package com.example.colourmemory.ui.main.viewholder

import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.colourmemory.R
import com.example.colourmemory.data.model.CardModel
import kotlinx.android.synthetic.main.view_holder_home.view.*

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val TAG = HomeViewHolder::class.java.canonicalName

    fun bind(cardModel: CardModel, isNeedToShowPlaceHolderImage: Boolean) {
        Log.d(
            TAG,
            "bind cardModel : $cardModel isNeedToShowPlaceHolderImage : $isNeedToShowPlaceHolderImage"
        )
        val drawableId =
            if (isNeedToShowPlaceHolderImage) R.drawable.card_bg else cardModel.cardDrawableId
        itemView.cardImg.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                drawableId
            )
        )
    }
}