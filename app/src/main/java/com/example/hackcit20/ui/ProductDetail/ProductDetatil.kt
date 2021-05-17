package com.example.hackcit20.ui.ProductDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.hackcit20.Adapter.CustomPagerAdapter
import com.example.hackcit20.R


class ProductDetatil : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.product_detatil_fragment, container, false)

        //val viewPager = view.findViewById(R.id.viewpager) as ViewPager
   //     viewPager.adapter = context?.let { CustomPagerAdapter(it,"d") }

        var toolbar: Toolbar =view.findViewById(R.id.ProducDetailFragmentToolbar)
        toolbar.inflateMenu(R.menu.trendingmenu)
            return view
    }



}