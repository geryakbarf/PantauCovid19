package com.ergnologi.pantaucovid19.ui

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ergnologi.pantaucovid19.R
import kotlinx.android.synthetic.main.activity_detail_artikel.*


class DetailArtikelActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_artikel)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.unikom)))
        supportActionBar?.title = "Detail Artikel"
        //Mengubah warna statusbar
        val window: Window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.unikom)
        //
        id = intent.getStringExtra("video")
        judulDetail.text = intent.getStringExtra("judul")
        penulisDetail.text = intent.getStringExtra("penulis")
        Glide.with(applicationContext).load(intent.getIntExtra("gambar", 0)).into(imgDetail)
        isiDetail.text = intent.getStringExtra("isi")
        btnVideo.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val intent = Intent(applicationContext, YoutubePlayerActivity::class.java)
        intent.putExtra("video", id)
        startActivity(intent)
    }
}