package com.example.hackcit20.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.hackcit20.R
import com.example.hackcit20.dataclass.ProductDetail

class SearechAdapter(private val context: Context, val category: List<ProductDetail>) :
    RecyclerView.Adapter<SearechAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val MovieImage: ImageView
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.blackimage)
            .error(R.drawable.blackimage)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()

        init {
            MovieImage = itemView.findViewById(R.id.item)
        }

        fun bind(position: Int) {
            Glide.with(context).load(category[position].ImageProfile)
                .apply(options)
                .into(MovieImage)
            itemView.setOnClickListener {
                val bundle = ToProductDetail(position)
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_search_Fragment_to_productDetatil, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.searchelementholder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder).bind(position)
    }

    override fun getItemCount(): Int {
        return category.size
    }

    private fun ToProductDetail(position: Int): Bundle {
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