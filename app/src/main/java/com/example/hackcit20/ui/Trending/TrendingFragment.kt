package com.example.hackcit20.ui.Trending

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackcit20.Activity.MainActivity2
import com.example.hackcit20.Activity.YoutbeActivity
import com.example.hackcit20.Adapter.YoutbeRecyler
import com.example.hackcit20.R
import com.example.hackcit20.dataclass.Api_key
import com.example.hackcit20.dataclass.YoutbeData

class TrendingFragment : Fragment(), YoutbeRecyler.OnCustomclickListener {

    private lateinit var adapter: YoutbeRecyler


    lateinit var viewModel: TrendingViewModel
    lateinit var pd:ProgressBar
    var apikey:Api_key? = null

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
        viewModel=ViewModelProviders.of(this).get(TrendingViewModel::class.java)
        viewModel.live_obj.observe(viewLifecycleOwner, Observer {
            live_obj->initRecylerView(view,live_obj)
        })
        pd=view.findViewById(R.id.progresbarloding)
        val toolbar: Toolbar = view.findViewById(R.id.TrendingFragmentToolbar)
        toolbar.inflateMenu(R.menu.trendingmenu)
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.searchitem) {
            }
            true
        }

        pd.visibility=View.VISIBLE
        apikey = (activity as MainActivity2?)?.passKey()

        return view
    }


    private fun initRecylerView(view: View, category: List<YoutbeData>) {
        pd.visibility=View.GONE
        val recyclerView = view.findViewById<RecyclerView>(R.id.youtubeRecycler)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = YoutbeRecyler(view.context, category, this)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onItemclick(category: List<YoutbeData>, position: Int) {
        if (apikey!=null) {
            val indent = Intent(context, YoutbeActivity::class.java)
            indent.putExtra("data", category[position].VideoId)
            indent.putExtra("key", apikey!!.YOUTUBE_API_KEY)
            startActivity(indent)
        }else{
            Toast.makeText(context,"Error Connecting !!",Toast.LENGTH_SHORT).show()
        }
    }





}

