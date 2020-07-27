package com.ergnologi.pantaucovid19.ui

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ergnologi.pantaucovid19.R
import com.ergnologi.pantaucovid19.adapter.ProvinsiAdapter
import com.ergnologi.pantaucovid19.api.GetDataApi
import com.ergnologi.pantaucovid19.models.ProvinsiModels
import com.ergnologi.pantaucovid19.response.ProvinsiResponse
import com.ergnologi.pantaucovid19.utils.Server
import kotlinx.android.synthetic.main.activity_provinsi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvinsiActivity : AppCompatActivity() {

    private val list: ArrayList<ProvinsiModels> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provinsi)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.darker)))
        supportActionBar?.title = "Data Provinsi"
        //Mengubah warna statusbar
        val window: Window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.darker)
        //
        rvProvinsi.setHasFixedSize(true)
        rvProvinsi.layoutManager = LinearLayoutManager(applicationContext)
        getDataProvinsi()
    }

    private fun getDataProvinsi() {
        val retrofit = Retrofit.Builder().baseUrl(Server.baseURL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(GetDataApi::class.java)
        val response = api.getDataProvinsi()
        response.enqueue(object : Callback<ProvinsiResponse> {
            override fun onFailure(call: Call<ProvinsiResponse>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Terjadi kesalahan, harap periksa jaringan anda!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(
                call: Call<ProvinsiResponse>,
                response: Response<ProvinsiResponse>
            ) {
                val data = response.body()?.data
                list.addAll(data!!)
                val adapter = ProvinsiAdapter(list)
                rvProvinsi.adapter = adapter
            }
        })
    }
}