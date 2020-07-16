package com.ergnologi.pantaucovid19.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ergnologi.pantaucovid19.R
import com.ergnologi.pantaucovid19.api.GetDataApi
import com.ergnologi.pantaucovid19.response.DataResponse
import com.ergnologi.pantaucovid19.utils.Server
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

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
                val days = arraySize - 1
                txtSembuh?.text = data.get(days).jumlahPasienSembuh
                txtMeninggal?.text = data.get(days).jumlahPasienMeninggal
                txtPerawatan?.text = data.get(days).jumlahpasiendalamperawatan
                txtPositif?.text = data.get(days).jumlahKasusKumulatif
                txtKasusBaru?.text = data.get(days).jumlahKasusBaruperHari
                txtKasusNegatif?.text = data.get(days).jumlahNegatif
                Toast.makeText(context, days.toString(), Toast.LENGTH_SHORT).show()
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
}
