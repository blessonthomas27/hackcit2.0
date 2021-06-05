package com.example.hackcit20.SecondClassUi.Cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackcit20.Adapter.CartAdapter
import com.example.hackcit20.R
import com.example.hackcit20.dataclass.ProductDetail


class Cart : Fragment() {
    private lateinit var CartviewModel: CartViewModel
    lateinit var pd: ProgressBar
    private lateinit var adapter: CartAdapter
    private val TAG = "MyActivity"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_card_details, container, false)

        pd=view.findViewById(R.id.progresbarlodingg)
        val CartList = mutableListOf<ProductDetail>()
        CartviewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)
        CartviewModel.live_obj.observe(viewLifecycleOwner, Observer { live_obj ->
            CartList.clear()
            CartList.addAll(live_obj)
            initRecylerView(view,CartList)
        })

        return view
    }
    private fun initRecylerView(view: View, category: List<ProductDetail>) {
        pd.visibility = View.GONE
        val recyclerView = view.findViewById<RecyclerView>(R.id.CartRecycler)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = CartAdapter(view.context, category)
        recyclerView.adapter = adapter
    }
}