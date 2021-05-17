package com.example.hackcit20.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.hackcit20.R
import com.example.hackcit20.dataclass.YoutbeData
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailLoader.OnThumbnailLoadedListener
import com.google.android.youtube.player.YouTubeThumbnailView


class YoutbeRecyler(private val context: Context, val category: List<YoutbeData>,var clicklistner:OnCustomclickListener) :
    RecyclerView.Adapter<YoutbeRecyler.ViewHolder>() {

    val key:String="AIzaSyDX9E43hGmQTi6fl0DM5aTzTCq87N0492g"



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val youTubeThumbnailView:YouTubeThumbnailView
        val VideoTitleName: TextView

        init {
                youTubeThumbnailView=itemView.findViewById(R.id.Thumbnail)
            VideoTitleName=itemView.findViewById(R.id.TitleName)
        }
        fun bind(position: Int,action: OnCustomclickListener) {
            val title=category[position].VideoTitle
            val videoId=category[position].VideoId
            VideoTitleName.text=title
            youTubeThumbnailView.initialize(key,object:YouTubeThumbnailView.OnInitializedListener{
                override fun onInitializationSuccess(
                    Thumnailviw: YouTubeThumbnailView,
                    loder: YouTubeThumbnailLoader
                ) {

                    loder.setVideo(videoId)


                    loder.setOnThumbnailLoadedListener(object :OnThumbnailLoadedListener{
                        override fun onThumbnailLoaded(p0: YouTubeThumbnailView?, p1: String?) {
                            loder.release();
                        }

                        override fun onThumbnailError(
                            p0: YouTubeThumbnailView?,
                            p1: YouTubeThumbnailLoader.ErrorReason?
                        ) {
                            Toast.makeText(context,"Thumbnailerror",Toast.LENGTH_LONG).show()
                        }
                    })
                }


                override fun onInitializationFailure(
                    Thumnailview: YouTubeThumbnailView?,
                    result: YouTubeInitializationResult?
                ) {
                    Toast.makeText(context,"Youtube Initialization Failure",Toast.LENGTH_LONG).show()
                }

            })

            itemView.setOnClickListener {
                action.onItemclick(category,position)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutbeRecyler.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.youtubeitemholder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: YoutbeRecyler.ViewHolder, position: Int) {
        (holder).bind(position,clicklistner)
    }



    override fun getItemCount(): Int {
        return category.size
    }

interface OnCustomclickListener{
    fun onItemclick(category: List<YoutbeData>, position: Int)
}

}