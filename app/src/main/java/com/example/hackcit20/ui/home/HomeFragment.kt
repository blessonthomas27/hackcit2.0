package com.example.hackcit20.ui.home

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackcit20.Adapter.HorizontelAdapter
import com.example.hackcit20.Adapter.MainRecylerAdapter
import com.example.hackcit20.R
import com.example.hackcit20.dataclass.Category
import com.example.hackcit20.dataclass.ProductDetail
import com.example.hackcit20.dataclass.ViewType

class
HomeFragment : Fragment() {


    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: MainRecylerAdapter
    var ImageUrl = arrayListOf<String>()
    lateinit var pd: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val toolbar: Toolbar = view.findViewById(R.id.HomeFragmentToolbar)
        pd = view.findViewById(R.id.progresbarloding)
        pd.visibility = View.VISIBLE
        val Animate = mutableListOf<ProductDetail>()
        val Tamil = mutableListOf<ProductDetail>()
        val hollywood = mutableListOf<ProductDetail>()
        val bollywood = mutableListOf<ProductDetail>()
        val obj = mutableListOf(
            ViewType(1, ImageUrl, "image slidingview"),
            ViewType(0, Category("Animation", "more", Animate), "", ""),
            ViewType(0, Category("Hollywood", "more", hollywood), "", ""),
            ViewType(0, Category("Bollywood", "more", bollywood), "", ""),
            ViewType(0, Category("Tamil", "more", Tamil), "", "")
        )

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.live_obj_Animate.observe(viewLifecycleOwner, Observer { live_obj ->
            Animate.clear()
            Animate.addAll(live_obj)
            initRecylerView(view, obj)
        })
        homeViewModel.live_obj_Tamil.observe(viewLifecycleOwner, Observer { live_obj ->
            Tamil.clear()
            Tamil.addAll(live_obj)
            initRecylerView(view, obj)
        })
        homeViewModel.live_obj_Bollywood.observe(viewLifecycleOwner, Observer { live_obj ->
            bollywood.clear()
            bollywood.addAll(live_obj)
            initRecylerView(view, obj)
        })
        homeViewModel.live_obj_Hollywood.observe(viewLifecycleOwner, Observer { live_obj ->
            hollywood.clear()
            hollywood.addAll(live_obj)
            initRecylerView(view, obj)
        })

        toolbar.inflateMenu(R.menu.homemenu)
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.searchitem) {
                Navigation.findNavController(view)
                    .navigate(R.id.action_navigation_home_to_search_Fragment)
            }
            true
        }
        ImageUrl.clear()
        ImageUrl()

        return view
    }

    private fun initRecylerView(view: View, category: List<ViewType>) {
        pd.visibility = View.GONE
        val recyclerView = view.findViewById<RecyclerView>(R.id.HomeFragmentRecylerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = MainRecylerAdapter(view.context, category)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun ImageUrl() {
        ImageUrl.add("https://wegotthiscovered.com/wp-content/uploads/2019/09/EFLLX8_U0AAjdOj-564x321.jpg")
        ImageUrl.add("https://wallpaperaccess.com/full/13453.jpg")
        ImageUrl.add("https://akm-img-a-in.tosshub.com/indiatoday/images/story/202012/Epu9aU6UYAEWzlI_1_1200x768.jpeg?HHe6y8vi627djL2vKKlKYcEly4BSfEnJ&size=770:433")
        ImageUrl.add("https://d2kektcjb0ajja.cloudfront.net/images/posts/feature_images/000/000/072/large-1466557422-feature.jpg?1466557422")
    }


}