package com.example.hackcit20.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hackcit20.R
import com.example.hackcit20.dataclass.Item

class HorizontelAdapter(
    val context: Context, val userlist: List<Item>,
    var clicklistner: HorizontelAdapter.OnHorzonatalClick
) :
    RecyclerView.Adapter<HorizontelAdapter.ViewHolder>() {
   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val MovieName: TextView
        var MovieImage: ImageView
        init {
            MovieName = itemView.findViewById(R.id.Item_Name)
            MovieImage=itemView.findViewById(R.id.Item_Image)
        }
        fun bind(position: Int,action: OnHorzonatalClick) {
            MovieName.text=userlist[position].ItemName
            MovieImage.setImageResource(userlist[position].ItemImage)
            itemView.setOnClickListener {
                action.onItemclickk(userlist,position)
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
        return userlist.size
    }

    override fun onBindViewHolder(holder: HorizontelAdapter.ViewHolder, position: Int) {
        (holder).bind(position,clicklistner)
    }

    interface OnHorzonatalClick{
        fun onItemclickk(userlist: List<Item>, position: Int){}
    }


}
