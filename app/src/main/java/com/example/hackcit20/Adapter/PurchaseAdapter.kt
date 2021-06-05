package com.example.hackcit20.Adapter

import android.content.Context
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

class PurchaseAdapter(private val context: Context, val category: List<ProductDetail>): RecyclerView.Adapter<PurchaseAdapter.ViewHolder>() {
    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val MovieName: TextView
        val MovieImage: ImageView
        val button:Button
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
            button=itemView.findViewById(R.id.removenow)
        }
        fun bind(position: Int) {
            MovieName.text=category[position].MovieName
            Glide.with(context).load(category[position].ImageProfile)
                .apply(options)
                .into(MovieImage)
            button.setOnClickListener {val bundle = Bundle()
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
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_downloads_to_productDetatil, bundle)  }
            itemView.setOnClickListener {
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
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_downloads_to_productDetatil, bundle)
            }
            }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.purchasetem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PurchaseAdapter.ViewHolder, position: Int) {
        (holder).bind(position)
    }

    override fun getItemCount(): Int {
        return category.size
    }
}