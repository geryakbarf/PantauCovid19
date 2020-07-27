package com.ergnologi.pantaucovid19.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ergnologi.pantaucovid19.R
import com.ergnologi.pantaucovid19.adapter.RumahSakitAdapter
import com.ergnologi.pantaucovid19.api.GetDataApi
import com.ergnologi.pantaucovid19.models.RumahSakitModels
import com.ergnologi.pantaucovid19.response.RumahSakitResponse
import com.ergnologi.pantaucovid19.utils.Server
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_rumah_sakit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RumahSakitActivity : AppCompatActivity() {

    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var lastLocation: Location? = null
    private val list: ArrayList<RumahSakitModels> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rumah_sakit)
        supportActionBar?.title = "Rumah Sakit Terdekat"
        //
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        rvRumahSakit.setHasFixedSize(true)
        rvRumahSakit.layoutManager = LinearLayoutManager(this)
    }

    private fun getData(lat: String, long: String) {
        val retrofit = Retrofit.Builder().baseUrl(Server.placeURL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(GetDataApi::class.java)
        val response = api.getRumahSakit(
            "$lat,$long",
            "5000",
            "rumah%20sakit",
            "hospital",
            "AIzaSyAQpq-8nVN-q-XripCN5ICUEO7E3Z0fFwE"
        )
        response.enqueue(object : Callback<RumahSakitResponse> {
            override fun onFailure(call: Call<RumahSakitResponse>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Terjadi kesalahan, harap periksa jaringan anda!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(
                call: Call<RumahSakitResponse>,
                response: Response<RumahSakitResponse>
            ) {
                val results = response.body()?.results
                list.addAll(results!!)
                val adapter = RumahSakitAdapter(list)
                rvRumahSakit.adapter = adapter
            }

        })
    }

    override fun onStart() {
        super.onStart()
        if (!checkPermissions()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions()
            }
        } else {
            getLastLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient?.lastLocation!!.addOnCompleteListener(this) { task ->
            if (task.isSuccessful && task.result != null) {
                lastLocation = task.result
                val lat = lastLocation!!.latitude.toString()
                val long = lastLocation!!.longitude.toString()
                getData(lat, long)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Gagal mendapatkan info lokasi, harap nyalakn GPS!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun checkPermissions(): Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(
            this@RumahSakitActivity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_PERMISSIONS_REQUEST_CODE
        )
    }

    private fun requestPermissions() {
        val shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (shouldProvideRationale) {
            showSnackbar("Membutuhkan izin untuk mengakses lokasi", "Oke",
                View.OnClickListener {
                    startLocationPermissionRequest()
                })
        } else {
            startLocationPermissionRequest()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> {
                    // Jika User tidak memberikan izin
                }
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> {
                    // Permission granted.
                    getLastLocation()
                }
                else -> {
                    showSnackbar("Izin tidak diberikan", "Pengaturan",
                        View.OnClickListener {
                            // Intent untuk membuka pengaturan
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts(
                                "package",
                                Build.DISPLAY, null
                            )
                            intent.data = uri
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }

    private fun showSnackbar(
        pesan: String, actionStringId: String,
        listener: View.OnClickListener
    ) {
        Toast.makeText(applicationContext, pesan, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    }

}