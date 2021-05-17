package com.example.hackcit20.Adapter


import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecylerSpaceItemDecoration(private val padding:Int):RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.right=padding
        outRect.bottom=50
    }
}