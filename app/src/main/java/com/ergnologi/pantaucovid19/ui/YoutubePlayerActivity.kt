package com.ergnologi.pantaucovid19.ui

import android.os.Bundle
import android.widget.Toast
import com.ergnologi.pantaucovid19.R
import com.ergnologi.pantaucovid19.utils.Server
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_youtube_player.*

class YoutubePlayerActivity : YouTubeBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_player)
        //z
        val kode = intent.getStringExtra("video")
        ytPlayer.initialize(Server.api, object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(
                    applicationContext,
                    "Terjadi Kesalahan, Periksa Koneksi Anda!",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }

            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                Toast.makeText(applicationContext, kode, Toast.LENGTH_SHORT).show()
                p1!!.loadVideo(kode)
            }
        })
    }
}