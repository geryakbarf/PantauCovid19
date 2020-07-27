package com.ergnologi.pantaucovid19.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ergnologi.pantaucovid19.R
import com.ergnologi.pantaucovid19.adapter.ArtikelAdapter
import com.ergnologi.pantaucovid19.data.ArtikelData
import com.ergnologi.pantaucovid19.models.ArtikelModels
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {

    private var list: ArrayList<ArtikelModels> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        supportActionBar?.title = "Artikel"
        //
        rvArtikel.setHasFixedSize(true)
        rvArtikel.layoutManager = LinearLayoutManager(this)
        list.addAll(ArtikelData.listData)
        showData()
    }

    private fun showData() {
        val adapter = ArtikelAdapter(list)
        rvArtikel.adapter = adapter
    }
}