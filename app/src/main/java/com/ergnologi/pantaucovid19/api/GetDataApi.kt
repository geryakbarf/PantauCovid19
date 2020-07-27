package com.ergnologi.pantaucovid19.api

import com.ergnologi.pantaucovid19.response.DataResponse
import com.ergnologi.pantaucovid19.response.ProvinsiResponse
import com.ergnologi.pantaucovid19.response.RumahSakitResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataApi {
    @GET("/api/harian")
    fun getData(): Call<DataResponse>

    @GET("/api/provinsi")
    fun getDataProvinsi(): Call<ProvinsiResponse>

    @GET("/maps/api/place/nearbysearch/json")
    fun getRumahSakit(
        @Query("location") location: String,
        @Query("radius") radius: String,
        @Query("name") name: String,
        @Query("type") type: String,
        @Query("key") key: String
    ): Call<RumahSakitResponse>


}