package com.example.hackcit20.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.hackcit20.R
import com.example.hackcit20.dataclass.ProductDetail
import com.example.hackcit20.dataclass.ViewType

class MainRecylerAdapter(private val context: Context, val category: List<ViewType>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), HorizontelAdapter.OnHorzonatalClick {
    var globalint: Int = 0


    inner class ItemListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        HorizontelAdapter.OnHorzonatalClick {
        val Title: TextView
        val More: TextView
        val recylerView: RecyclerView

        init {
            Title = itemView.findViewById(R.id.ProductItemHolder_title)
            More = itemView.findViewById(R.id.ProductItemHolder_more)
            recylerView = itemView.findViewById(R.id.ProductItemHolder_reyelerview)
        }

        fun bind(position: Int) {
            val a = category[position].dataa
            Title.text = a.Category
            More.text = a.more
            setProductItemRecyler(recylerView, category[position].dataa.items, itemView)

        }

        override fun onItemclickk(productDetail: List<ProductDetail>, position: Int) {
            val bundle = Bundle()
            bundle.apply {
                putString("ImageBanner", productDetail[position].ImageBanner)
                putString("ImageProfile", productDetail[position].ImageProfile)
                putString("MovieName", productDetail[position].MovieName)
                putString("MovieDescription", productDetail[position].MovieDescription)
                putString("Price", productDetail[position].Price)
                putString("duration", productDetail[position].duration)
                putString("grade", productDetail[position].grade)
                putString("review", productDetail[position].review)
            }
            Navigation.findNavController(itemView)
                .navigate(R.id.action_navigation_home_to_productDetatil, bundle)
        }
    }

    inner class ImageslidingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Img: ImageSlider

        init {
            Img = itemView.findViewById(R.id.Imageslider)
        }

        fun bind(position: Int) {
            val a = category[position].ImaguRL
            imageslider(Img, a)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return category[position].viewtype
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            globalint += 1
            return ImageslidingHolder(
                LayoutInflater.from(context).inflate(R.layout.homecarousal, parent, false)
            )
        } else {
            return ItemListViewHolder(
                LayoutInflater.from(context).inflate(R.layout.itemholder, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return category.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (category[position].viewtype == 1) {
            (holder as ImageslidingHolder).bind(position)
        } else {
            (holder as ItemListViewHolder).bind(position)
        }
    }

    private fun setProductItemRecyler(
        recylerView: RecyclerView,
        category: List<ProductDetail>,
        itemView: View
    ) {
        val itemRecylerAdapter = HorizontelAdapter(context, category, ItemListViewHolder(itemView))
        recylerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val itemDecoration = RecylerSpaceItemDecoration(15)
        recylerView.addItemDecoration(itemDecoration)
        recylerView.adapter = itemRecylerAdapter
        itemRecylerAdapter.notifyDataSetChanged()
    }

    fun imageslider(imageslider: ImageSlider, ImaguRL: List<String>) {
        val slide_model: ArrayList<SlideModel> = ArrayList<SlideModel>()

        for (i in ImaguRL) {
            slide_model.add(SlideModel(i))
        }
        imageslider.setImageList(slide_model, false)
    }
}