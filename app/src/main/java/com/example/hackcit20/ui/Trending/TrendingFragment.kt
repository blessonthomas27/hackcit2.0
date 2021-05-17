package com.example.hackcit20.ui.Trending

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackcit20.Adapter.YoutbeRecyler
import com.example.hackcit20.R
import com.example.hackcit20.Activity.YoutbeActivity
import com.example.hackcit20.dataclass.YoutbeData


class TrendingFragment : Fragment(),YoutbeRecyler.OnCustomclickListener {


    private lateinit var adapter: YoutbeRecyler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trending, container, false)



        var toolbar: Toolbar =view.findViewById(R.id.TrendingFragmentToolbar)
        toolbar.inflateMenu(R.menu.trendingmenu)
        toolbar.setOnMenuItemClickListener {
            if(it.itemId==R.id.searchitem){
            }
            true
        }
        val obj= mutableListOf(
            YoutbeData("Jm0MLlE4x0U","The Fox and the Bird - CGI short film by Fred and Sam Guillaume",""),
            YoutbeData("jprSALDoYX8","PIPER Disney Pixar Full Short Film Official Promos | Finding Dory Bonus (2016) Animation Adventure",""),
            YoutbeData("XnuDetmX9lU","TOP UPCOMING ANIMATION MOVIES 2020 & 2021 (Trailers)",""),
            YoutbeData("Kd2srYijPhc","The Smallest Parrot you have ever seen - Tiny egg rescue",""),
            YoutbeData("7xTmyUdqDfM","BAO Disney Pixar Full Short Film Official Promos | Incredibles 2 Bonus (2018) Animation Adventure HD","")
        )

        initRecylerView(view,obj)

        return view
    }
    private fun initRecylerView(view: View,category: List<YoutbeData>){
        val recyclerView=view.findViewById<RecyclerView>(R.id.youtubeRecycler)
        recyclerView.layoutManager= LinearLayoutManager(activity)
        adapter= YoutbeRecyler(view.context,category,this)
        recyclerView.adapter=adapter
    }

    override fun onItemclick(category: List<YoutbeData>, position: Int) {
        Toast.makeText(context,"clicked",Toast.LENGTH_LONG).show()
        val indent=Intent(context, YoutbeActivity::class.java)
        indent.putExtra("data",category[position].VideoId)
        startActivity(indent)
    }

}

