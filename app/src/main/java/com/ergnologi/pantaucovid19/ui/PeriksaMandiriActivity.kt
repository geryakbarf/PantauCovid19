package com.ergnologi.pantaucovid19.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.ergnologi.pantaucovid19.R
import com.ergnologi.pantaucovid19.data.PertanyaanData
import com.ergnologi.pantaucovid19.models.PertanyaanModels
import kotlinx.android.synthetic.main.activity_periksa_mandiri.*

class PeriksaMandiriActivity : AppCompatActivity(), View.OnClickListener {

    private var i = 0
    private var j = 0
    private var list: ArrayList<PertanyaanModels> = arrayListOf()
    private var point = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_periksa_mandiri)
        supportActionBar?.hide()
        //Mengubah warna statusbar
        val window: Window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.blue_darker)
        //
        list.addAll(PertanyaanData.listData)
        getData()
        btnIya.setOnClickListener(this)
        btnTidak.setOnClickListener(this)
    }

    private fun getData() {
        val soal = i + 1
        j = list.size
        txtNoSoal.text = "Pertanyaan Ke : $soal"
        txtPertanyaan.text = list[i].pertanyaan
        i += 1
    }

    private fun checkAnswer(answer: String) {
        if (answer.equals("Iya"))
            point += 10
    }

    private fun validation() {
        if (i == j) {
            val intent = Intent(applicationContext, ResultActivity::class.java)
            intent.putExtra("point", point)
            startActivity(intent)
            finish()
        } else
            getData()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnIya -> {
                checkAnswer(txtIya.text.toString())
                validation()
            }
            R.id.btnTidak -> {
                checkAnswer(txtTidak.text.toString())
                validation()
            }
        }
    }
}