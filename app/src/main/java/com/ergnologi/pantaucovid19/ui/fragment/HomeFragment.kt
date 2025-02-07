package com.ergnologi.pantaucovid19.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ergnologi.pantaucovid19.R
import com.ergnologi.pantaucovid19.api.GetDataApi
import com.ergnologi.pantaucovid19.response.DataResponse
import com.ergnologi.pantaucovid19.ui.ArticleActivity
import com.ergnologi.pantaucovid19.ui.ProvinsiActivity
import com.ergnologi.pantaucovid19.ui.SeputarCovidActivity
import com.ergnologi.pantaucovid19.utils.Server
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        currentDate()
        btnDataProvinsi.setOnClickListener(this)
        btnCallDarurat.setOnClickListener(this)
        btnArtikel.setOnClickListener(this)
        btnFAQ.setOnClickListener(this)
    }

    private fun currentDate() {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy")
        val now = dateFormat.format(Date())
        txtTanggal.text = now
    }

    override fun onResume() {
        super.onResume()
        shimmerMeninggal?.startShimmer()
        shimmerODP?.startShimmer()
        shimmerPDP?.startShimmer()
        shimmerPerawatan?.startShimmer()
        shimmerPositif?.startShimmer()
        shimmerSembuh?.startShimmer()
    }

    override fun onPause() {
        shimmerMeninggal?.stopShimmer()
        shimmerODP?.stopShimmer()
        shimmerPDP?.stopShimmer()
        shimmerPerawatan?.stopShimmer()
        shimmerPositif?.stopShimmer()
        shimmerSembuh?.stopShimmer()
        super.onPause()
    }

    private fun getData() {
        //Deklarasi Retrofit
        val retrofit = Retrofit.Builder().baseUrl(Server.baseURL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        //Deklarasi API
        val api = retrofit.create(GetDataApi::class.java)
        val response = api.getData()
        response.enqueue(object : Callback<DataResponse> {
            //Jika gagal mendapatkan data
            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Toast.makeText(
                    view?.context,
                    "Gagal mendapatkan data, harap periksa jaringan anda !",
                    Toast.LENGTH_SHORT
                ).show()
                hideShimmer()
            }

            //Jika sukses mendapatkan data
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                val data = response.body()?.data
                val arraySize = data!!.size
                val days = arraySize - 2
                txtSembuh?.text = data[days].jumlahPasienSembuh
                txtMeninggal?.text = data[days].jumlahPasienMeninggal
                txtPerawatan?.text = data[days].jumlahpasiendalamperawatan
                txtPositif?.text = data[days].jumlahKasusKumulatif
                txtKasusBaru?.text = data[days].jumlahKasusBaruperHari
                txtKasusNegatif?.text = data[days].jumlahNegatif
                hideShimmer()
            }
        })
    }

    private fun hideShimmer() {
        //Hide Shimmer Animation
        shimmerMeninggal?.stopShimmer()
        shimmerODP?.stopShimmer()
        shimmerPDP?.stopShimmer()
        shimmerPerawatan?.stopShimmer()
        shimmerPositif?.stopShimmer()
        shimmerSembuh?.stopShimmer()
        shimmerMeninggal?.visibility = View.GONE
        shimmerODP?.visibility = View.GONE
        shimmerPDP?.visibility = View.GONE
        shimmerPerawatan?.visibility = View.GONE
        shimmerPositif?.visibility = View.GONE
        shimmerSembuh?.visibility = View.GONE
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnDataProvinsi -> startActivity(Intent(context, ProvinsiActivity::class.java))
            R.id.btnCallDarurat -> {
                val intent = Uri.parse("tel:119").let { number ->
                    Intent(Intent.ACTION_DIAL, number)
                }
                startActivity(intent)
            }
            R.id.btnArtikel -> startActivity(Intent(context, ArticleActivity::class.java))
            R.id.btnFAQ -> startActivity(Intent(context, SeputarCovidActivity::class.java))
        }
    }
}
