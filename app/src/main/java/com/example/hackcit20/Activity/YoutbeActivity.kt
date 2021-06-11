package com.example.hackcit20.Activity

import android.os.Bundle
import android.widget.Toast
import com.example.hackcit20.R
import com.example.hackcit20.dataclass.Api_key
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class YoutbeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtbe)

        val youTubeView=findViewById<YouTubePlayerView>(R.id.youtubeplayer)

        youTubeView.initialize(intent.getStringExtra("key"), this);
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        sucess: Boolean
    ) {
        if (!sucess) {
            player?.loadVideo(intent.getStringExtra("data"));
        }else{
            onBackPressed()
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        errorResulr: YouTubeInitializationResult?
    ) {
        onBackPressed()
    }
}