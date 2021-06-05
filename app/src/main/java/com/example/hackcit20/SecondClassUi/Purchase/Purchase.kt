package com.example.hackcit20.SecondClassUi.Purchase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackcit20.Adapter.PurchaseAdapter
import com.example.hackcit20.R
import com.example.hackcit20.dataclass.ProductDetail

class Purchase : Fragment() {

    private lateinit var PurchaseviewModel: PurchaseViewModel
    lateinit var pd: ProgressBar
    private lateinit var adapter: PurchaseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_purchase, container, false)

        pd=view.findViewById(R.id.progresbarlodingg)
        val purchaselist = mutableListOf<ProductDetail>(
        )
        PurchaseviewModel = ViewModelProviders.of(this).get(PurchaseViewModel::class.java)
       PurchaseviewModel.live_obj.observe(viewLifecycleOwner, Observer { live_obj ->
            purchaselist.clear()

            purchaselist.addAll(live_obj)
           pd.visibility = View.GONE
           initRecylerView(view,purchaselist)
        })

        return view
    }
    private fun initRecylerView(view: View, category: List<ProductDetail>) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.PurchaseRecycler)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = PurchaseAdapter(view.context, category)
        recyclerView.adapter = adapter
    }

}