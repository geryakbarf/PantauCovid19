package com.ergnologi.pantaucovid19.api

import com.ergnologi.pantaucovid19.response.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface GetDataApi {
    @GET("/api/harian")
    fun getData(): Call<DataResponse>
}