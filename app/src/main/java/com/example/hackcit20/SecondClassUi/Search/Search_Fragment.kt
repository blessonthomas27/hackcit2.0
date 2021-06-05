package com.example.hackcit20.SecondClassUi.Search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hackcit20.Adapter.SearechAdapter
import com.example.hackcit20.R
import com.example.hackcit20.dataclass.ProductDetail
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class Search_Fragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var adapter: SearechAdapter
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionReference: CollectionReference = db.collection("Products")
    private var list = mutableListOf<ProductDetail>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_, container, false)
        val toolbar: Toolbar = view.findViewById(R.id.SearchFragmentToolbar)
        toolbar.inflateMenu(R.menu.trendingmenu)
        val searchView: SearchView = view.findViewById(R.id.search)

        initRecylerView(view,list)
        searchView.setOnQueryTextListener(this)
        return view
    }
    private fun initRecylerView(view: View, category: List<ProductDetail>) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.SearchFragmentRecylerView)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        adapter = SearechAdapter(view.context, category)
        recyclerView.adapter = adapter

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val query: Query = collectionReference.whereEqualTo("MovieName", newText)
        try {
            query.get().addOnCompleteListener {
                if (it.isSuccessful) {
                    var i = 0
                    for (document in it.result!!) {
                        val temp: ProductDetail = document.toObject(ProductDetail::class.java)
                        list.add(i++, temp)
                    }
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: Exception) {

        }
        return true
    }

}