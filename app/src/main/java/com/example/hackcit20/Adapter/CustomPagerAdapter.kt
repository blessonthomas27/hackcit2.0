package com.example.hackcit20.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.hackcit20.R


class CustomPagerAdapter(context: Context,St:String) : PagerAdapter() {
    private val mContext: Context

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        if (position==0) {
            val inflater = LayoutInflater.from(mContext)
            val layout =
                inflater.inflate(R.layout.overview, collection, false) as ViewGroup
            collection.addView(layout)
            return layout
        }else if (position==1){
            val inflater = LayoutInflater.from(mContext)
            val layout =
                inflater.inflate(R.layout.crewmembers, collection, false) as ViewGroup
            collection.addView(layout)
            return layout
        }else {
            val inflater = LayoutInflater.from(mContext)
            val layout =
                inflater.inflate(R.layout.comments, collection, false) as ViewGroup
            collection.addView(layout)
            return layout
        }

    }

    override fun destroyItem(
        collection: ViewGroup,
        position: Int,
        view: Any
    ) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return 3
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }



    init {
        mContext = context
    }
}