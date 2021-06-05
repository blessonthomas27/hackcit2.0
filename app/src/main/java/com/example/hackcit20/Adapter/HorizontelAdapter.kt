package com.example.hackcit20.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.hackcit20.R
import com.example.hackcit20.dataclass.ProductDetail


class HorizontelAdapter(
    val context: Context, val productDetail: List<ProductDetail>,
    var clicklistner: HorizontelAdapter.OnHorzonatalClick
) :
    RecyclerView.Adapter<HorizontelAdapter.ViewHolder>() {
   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val MovieName: TextView
        var MovieImage: ImageView
       val options: RequestOptions = RequestOptions()
           .centerCrop()
           .placeholder(R.drawable.blackimage)
           .error(R.drawable.blackimage)
           .diskCacheStrategy(DiskCacheStrategy.ALL)
           .priority(Priority.HIGH)
           .dontAnimate()
           .dontTransform()
        init {
            MovieName = itemView.findViewById(R.id.Item_Name)
            MovieImage=itemView.findViewById(R.id.Item_Image)
        }
        fun bind(position: Int,action: OnHorzonatalClick) {
            Glide.with(context).load(productDetail[position].ImageProfile)
                .apply(options)
                .into(MovieImage)
            itemView.setOnClickListener {
                action.onItemclickk(productDetail,position)
            }
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontelAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productDetail.size
    }

    override fun onBindViewHolder(holder: HorizontelAdapter.ViewHolder, position: Int) {
        (holder).bind(position,clicklistner)
    }

    interface OnHorzonatalClick{
        fun onItemclickk(productDetail: List<ProductDetail>, position: Int){}
    }


}
