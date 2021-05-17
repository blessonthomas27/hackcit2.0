package com.example.hackcit20.ui.home

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackcit20.Adapter.HorizontelAdapter
import com.example.hackcit20.Adapter.MainRecylerAdapter
import com.example.hackcit20.R
import com.example.hackcit20.dataclass.Category
import com.example.hackcit20.dataclass.Item
import com.example.hackcit20.dataclass.ViewType

class HomeFragment : Fragment(),HorizontelAdapter.OnHorzonatalClick {


    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: MainRecylerAdapter
    var ImageUrl = arrayListOf<String>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        var toolbar: Toolbar =view.findViewById(R.id.HomeFragmentToolbar)

        toolbar.inflateMenu(R.menu.homemenu)
        toolbar.setOnMenuItemClickListener {
            if(it.itemId==R.id.searchitem){
                Toast.makeText(context,"clicked ",Toast.LENGTH_LONG).show()
            }
            true
        }
        ImageUrl.clear()
        ImageUrl()
            var datalist1 = mutableListOf(
                Item("aaa", R.drawable.ic_launcher_background),
                Item("", R.drawable.imaddges),
                Item("", R.drawable.imagesss),
                Item("", R.drawable.spiderman),
                Item("", R.drawable.gaurdiunglxy),
                Item("", R.drawable.images),
                Item("", R.drawable.aaaaaaaaa),
                Item("", R.drawable.ic_launcher_background),
                Item("", R.drawable.ic_launcher_background),
                Item("", R.drawable.ic_launcher_background)
            )

            val obj = mutableListOf(
                ViewType(1, ImageUrl, "image slidingview"),
                ViewType(0,Category("Trop Grossing", "more", datalist1),"",""),
                ViewType(0,Category("Hollywood", "more", datalist1),"",""),
                ViewType(0,Category("Tamil", "more", datalist1),"",""),
                ViewType(0,Category("Bollywood", "more", datalist1),"",""),
                ViewType(0,Category("South Special", "more", datalist1),"","")
            )


        initRecylerView(view,obj)
        return view
    }
    private fun initRecylerView(view: View,category: List<ViewType>){
        val recyclerView=view.findViewById<RecyclerView>(R.id.HomeFragmentRecylerView)
        recyclerView.layoutManager=LinearLayoutManager(activity)
        adapter=MainRecylerAdapter(view.context,category)
        recyclerView.adapter=adapter
    }
    fun ImageUrl() {
        ImageUrl.add("https://wegotthiscovered.com/wp-content/uploads/2019/09/EFLLX8_U0AAjdOj-564x321.jpg")
        ImageUrl.add("https://wallpaperaccess.com/full/13453.jpg")
        ImageUrl.add("https://akm-img-a-in.tosshub.com/indiatoday/images/story/202012/Epu9aU6UYAEWzlI_1_1200x768.jpeg?HHe6y8vi627djL2vKKlKYcEly4BSfEnJ&size=770:433")
        ImageUrl.add("https://d2kektcjb0ajja.cloudfront.net/images/posts/feature_images/000/000/072/large-1466557422-feature.jpg?1466557422")
    }


}