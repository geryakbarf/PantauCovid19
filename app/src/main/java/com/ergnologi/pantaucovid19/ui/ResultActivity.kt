package com.ergnologi.pantaucovid19.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ergnologi.pantaucovid19.R
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity(), View.OnClickListener {

    var point = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.hide()
        //
        point = intent.getIntExtra("point", 0)
        btnSelesai.setOnClickListener(this)
        checkHasil()
    }

    private fun checkHasil() {
        if (point <= 30) {
            Glide.with(applicationContext).load(R.drawable.sehat).into(imgHasil)
            txtHasil.text = "Anda Sangat Baik"
            txtKeterangan.text =
                "Kondisi anda baik - baik saja. Yang perlu anda lakukan adalah tetap" +
                        "ikuti protokol kesehatan seperti sering mencuci tangan dan rajin berolahraga."
        } else if (point in 31..60) {
            Glide.with(applicationContext).load(R.drawable.medium).into(imgHasil)
            txtHasil.text = "Lakukan Isolasi Mandiri"
            txtKeterangan.text =
                "Hasil diagnosis dari aplikasi ini memang bukan pengganti hasil diagnosis dari dokter, namun alangkah lebik baiknya" +
                        " jika anda melakukan isolasi mandiri selama 14 hari dan tetap mengikuti protokol kesehatan."
        } else if (point > 60) {
            Glide.with(applicationContext).load(R.drawable.sakit).into(imgHasil)
            txtHasil.text = "Periksalah Ke Dokter"
            txtKeterangan.text =
                "Hasil diagnosis dari aplikasi ini memang bukan pengganti hasil diagnosis dari dokter, namun kami sangat menyarankan anda" +
                        " untuk memeriksa kondisi kesehatan anda ke rumah sakit atau dokter terdekat anda."
        }
    }

    override fun onClick(p0: View?) {
        finish()
    }

}