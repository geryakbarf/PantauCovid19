package com.ergnologi.pantaucovid19.api

import com.ergnologi.pantaucovid19.response.DataResponse
import com.ergnologi.pantaucovid19.response.ProvinsiResponse
import retrofit2.Call
import retrofit2.http.GET

interface GetDataApi {
    @GET("/api/harian")
    fun getData(): Call<DataResponse>

    @GET("/api/provinsi")
    fun getDataProvinsi(): Call<ProvinsiResponse>
}