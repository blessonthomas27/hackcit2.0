package com.example.hackcit20.Adapter

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.hackcit20.R
import com.example.hackcit20.dataclass.ProductDetail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CartAdapter(private val context: Context, val category: List<ProductDetail>): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val MovieName: TextView
        val MovieImage: ImageView
        val buy: Button
        val remove: Button
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.blackimage)
            .error(R.drawable.blackimage)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()
        init {
            MovieName = itemView.findViewById(R.id.textView11)
            MovieImage=itemView.findViewById(R.id.productimgsmall)
            remove=itemView.findViewById(R.id.removenow)
            buy=itemView.findViewById(R.id.BuyNow)
        }
        fun bind(position: Int) {
            MovieName.text=category[position].MovieName
            Glide.with(context).load(category[position].ImageProfile)
                .apply(options)
                .into(MovieImage)
            buy.setOnClickListener {
                val bundle=ToProductDetail(position)
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_cardDetails_to_productDetatil, bundle) }
            itemView.setOnClickListener {
               val bundle=ToProductDetail(position)
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_cardDetails_to_productDetatil, bundle)
            }
            remove.setOnClickListener {
                val firestore: FirebaseFirestore= FirebaseFirestore.getInstance()
                val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
                val UID=mAuth.currentUser?.uid
                firestore.collection(UID.toString()).document("Cart").collection("list")
                    .document(MovieName.text.toString()).delete()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cartitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        (holder).bind(position)
    }

    override fun getItemCount(): Int {
        return category.size
    }

    private fun ToProductDetail(position: Int):Bundle{
        val bundle = Bundle()
        bundle.apply {
            putString("ImageBanner", category[position].ImageBanner)
            putString("ImageProfile", category[position].ImageProfile)
            putString("MovieName", category[position].MovieName)
            putString("MovieDescription", category[position].MovieDescription)
            putString("Price", category[position].Price)
            putString("duration", category[position].duration)
            putString("grade", category[position].grade)
            putString("review", category[position].review)
        }

        return bundle
    }
}