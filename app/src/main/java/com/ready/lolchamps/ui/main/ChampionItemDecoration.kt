package com.ready.lolchamps.ui.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ready.lolchamps.utils.dpToPx

class ChampionItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spacePx = view.context.dpToPx(BETWEEN_SPACE)
        if (parent.getChildAdapterPosition(view) % 2 != 0) {
            outRect.left = spacePx
        } else {
            outRect.right = spacePx
        }
    }

    companion object {
        private const val BETWEEN_SPACE = 4f
    }
}